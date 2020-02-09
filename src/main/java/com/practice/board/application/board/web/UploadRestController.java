package com.practice.board.application.board.web;

import com.practice.board.application.board.domain.AttachFile;
import com.practice.board.commons.utils.UploadFileUtils;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import net.sf.jmimemagic.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/board")
public class UploadRestController {

    private static final String UPLOAD_DIR = "C:\\practice-board\\";

    // 파일 업로드를 위해 ajax 요청을 하면 해당 컨트롤러로 들어온다.
    @PostMapping(value = "/upload")
    public ResponseEntity<AttachFile> upload(MultipartFile uploadFile) {
        ResponseEntity<AttachFile> entity = null;
        try {
            entity = new ResponseEntity<>(UploadFileUtils.uploadFile(uploadFile), HttpStatus.CREATED);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {
        File file = new File(UPLOAD_DIR + UploadFileUtils.getDatePath(), fileName);
        ResponseEntity<byte[]> result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", UploadFileUtils.getMimeType(file));

        try {
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
