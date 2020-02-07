package com.practice.board.application.board.web;

import com.practice.board.application.board.domain.AttachFile;
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

    private static final String UPLOAD_DIR = "/Users/seung7642/neowiz-data/board";

    // 파일 업로드를 위해 ajax 요청을 하면 해당 컨트롤러로 들어온다.
    @PostMapping(value = "/upload")
    public ResponseEntity<List<AttachFile>> upload(MultipartFile[] uploadFile, RedirectAttributes redirectAttributes) {
        log.info("넘어온 첨부파일 정보 : {}", uploadFile);
        List<AttachFile> list = new ArrayList<>();

        // 년/월/일 단위로 하위 디렉터리 생성 ( 한 디렉터리에 너무 많은 파일이 생성되는 문제 방지 )
        File uploadPath = new File(UPLOAD_DIR, getDirectory());
        log.info("upload path: " + uploadPath);
        if (!uploadPath.exists()) uploadPath.mkdirs();

        for (MultipartFile multipartFile : uploadFile) {
            log.info("---------------------");
            log.info("Upload File Name: " + multipartFile.getOriginalFilename());
            log.info("Upload File Size: " + multipartFile.getSize());

            AttachFile attachFile = new AttachFile();

            try {
                String uploadFileName = getUploadFileName(multipartFile);
                File saveFile = new File(uploadPath, uploadFileName);
                multipartFile.transferTo(saveFile);
                attachFile.setUuid(uploadFileName.split("_")[0]);

                // 이미지 파일이라면 섬네일 이미지를 생성한다.
                if (checkImageType(saveFile)) {
                    attachFile.setImage(true);
                    FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
                    Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
                    thumbnail.close();
                }

                attachFile.setFileName(multipartFile.getOriginalFilename());
                attachFile.setUploadPath(getDirectory());

                list.add(attachFile);
            } catch (Exception e) {
                log.error("파일첨부 작업 중 예외 발생 : {}", e.getMessage());
            }
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {
        log.info("fileName : {}", fileName);

        File file = new File(UPLOAD_DIR + fileName);
        ResponseEntity<byte[]> result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", getMimeType(file));

        try {
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
        }

        return result;
    }

    /**
     * 한 디렉터리에 너무 많은 파일이 업로드되는 문제를 방지하기 위하여, 년/월/일에 해당하는 하위 디렉터리를 생성하기 위해
     * 오늘 날짜를 기준으로 년/월/일 값을 구한다.
     *
     * @return String
     */
    private String getDirectory() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        return str.replace("-", File.separator);
    }

    /**
     * 실제 업로드할 때 사용될 파일명을 구한다.
     * 중복된 파일명을 예방하기 위해 원본 파일명 앞에 랜덤한 UUID 값을 추가한다.
     *
     * @param multipartFile
     * @return String
     */
    private String getUploadFileName(MultipartFile multipartFile) {
        // 원본 파일명 앞에 랜덤한 UUID 값을 추가해서 중복된 파일이름 문제를 방지한다.
        // ex) uuid_파일명
        return UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
    }

    /**
     * 파일의 MIME 타입으로 이미지 파일인지 확인한다.
     *
     * @param file
     * @return boolean
     */
    private boolean checkImageType(File file) {
        String mime = getMimeType(file);
        if (!mime.isEmpty()) return mime.contains("image");

        return false;
    }

    /**
     * 파일의 MIME 타입을 가져온다.
     *
     * @param file
     * @return String
     */
    private String getMimeType(File file) {
        try {
            return Magic.getMagicMatch(file, false).getMimeType();
        } catch (MagicParseException e) {
            log.debug(e.getMessage(), e);
        } catch (MagicMatchNotFoundException e) {
            log.debug(e.getMessage(), e);
        } catch (MagicException e) {
            log.debug(e.getMessage(), e);
        }
        return "";
    }
}
