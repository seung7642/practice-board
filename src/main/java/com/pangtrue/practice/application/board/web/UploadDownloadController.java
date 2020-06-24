package com.pangtrue.practice.application.board.web;

import com.pangtrue.practice.application.board.domain.AttachFile;
import com.pangtrue.practice.application.board.domain.BoardAttach;
import com.pangtrue.practice.application.board.service.UploadDownloadService;
import com.pangtrue.practice.commons.exception.ResourceNotFoundException;
import com.pangtrue.practice.commons.utils.DownloadFileUtils;
import com.pangtrue.practice.commons.utils.UploadFileUtils;
import lombok.AllArgsConstructor;
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
import java.util.Optional;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 2. 6.
 * Time: 오전 9:50
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/board")
public class UploadDownloadController {

    private static final String UPLOAD_PATH = "/Users/seung7642/neowiz-data/board";
    private final UploadDownloadService uploadDownloadService;

    @PostMapping("/upload")
    public ResponseEntity upload(MultipartFile uploadFile) {
        try {
            return ResponseEntity.of(Optional.of(UploadFileUtils.uploadFile(uploadFile)));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.of(null);
        }
    }

    @GetMapping("/display")
    public ResponseEntity display(@RequestParam("filename") String fileName) {
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", UploadFileUtils.getMimeType(fileName));
            return new ResponseEntity<>(UploadFileUtils.displayFile(fileName), headers, HttpStatus.OK);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.of(null);
        }
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity download(@RequestHeader("User-Agent") String userAgent,
                                   @RequestParam("filename") String fileName) {

        Resource resource = new FileSystemResource(UPLOAD_PATH + "/" + fileName);
        if (!resource.exists()) {
            throw new ResourceNotFoundException();
        }

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Disposition",
                    "attachment; filename=" + DownloadFileUtils.getDownloadName(userAgent, resourceName));
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (UnsupportedEncodingException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.of(null);
        } catch (ResourceNotFoundException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.of(null);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.of(null);
        }
    }

    @GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getAttachList(int boardIdx) {
        return ResponseEntity.of(Optional.of(uploadDownloadService.getAttachList(boardIdx)));
    }
}
