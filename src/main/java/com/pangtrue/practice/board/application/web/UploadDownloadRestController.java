package com.pangtrue.practice.board.application.web;

import com.pangtrue.practice.board.application.domain.AttachFile;
import com.pangtrue.practice.board.application.domain.BoardAttach;
import com.pangtrue.practice.board.application.service.UploadDownloadService;
import com.pangtrue.practice.board.commons.exception.ResourceNotFoundException;
import com.pangtrue.practice.board.commons.utils.DownloadFileUtils;
import com.pangtrue.practice.board.commons.utils.UploadFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 2. 6.
 * Time: 오전 9:50
 */
@Slf4j
@RestController
@RequestMapping(value = "/board")
public class UploadDownloadRestController {

    private static final String UPLOAD_PATH = "/Users/seung7642/neowiz-data/board";
    private final UploadDownloadService uploadDownloadService;

    @Autowired
    public UploadDownloadRestController(UploadDownloadService uploadDownloadService) {
        this.uploadDownloadService = uploadDownloadService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<AttachFile> upload(MultipartFile uploadFile) {
        ResponseEntity<AttachFile> entity = null;

        try {
            entity = new ResponseEntity<>(UploadFileUtils.uploadFile(uploadFile), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("파일첨부 작업 중 예외 발생 : {}", e.getMessage());
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @GetMapping(value = "/display")
    public ResponseEntity<byte[]> display(@RequestParam("filename") String fileName) {
        log.info("브라우저에 노출시킬 이미지 파일명 : {}", fileName);
        ResponseEntity<byte[]> result = null;
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", UploadFileUtils.getMimeType(fileName));
            result = new ResponseEntity<>(UploadFileUtils.displayFile(fileName), headers, HttpStatus.OK);
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String userAgent,
                                             @RequestParam("filename") String fileName) {
        log.info("다운로드할 파일명 : {}", fileName);

        ResponseEntity<Resource> entity = null;
        Resource resource = new FileSystemResource(UPLOAD_PATH + "/" + fileName);
        if (!resource.exists()) {
            throw new ResourceNotFoundException();
        }
        log.info("resource : {}", resource);

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Disposition",
                    "attachment; filename=" + DownloadFileUtils.getDownloadName(userAgent, resourceName));
            entity = new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            entity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<BoardAttach>> getAttachList(int boardIdx) {
        return new ResponseEntity<>(uploadDownloadService.getAttachList(boardIdx), HttpStatus.OK);
    }
}
