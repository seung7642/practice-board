package com.pangtrue.practice.commons.utils;

import com.pangtrue.practice.application.board.domain.AttachFile;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 2. 7.
 * Time: 오후 5:23
 *
 * 해당 업로드용 유틸리티 클래스가 하는 일
 *     1. 중복된 파일명 문제를 예방하기 위한 업로드한 파일명 앞에 랜덤한 UUID 값 추가
 *     2. 한 디렉터리에 너무 많은 파일이 생성되는 문제를 예방하기 위해 날짜를 기준으로 년/월/일 하위 디렉터리 생성
 *     3. 이미지 파일인 경우에 섬네일 이미지 생성
 */
@Slf4j
public class UploadFileUtils {

    private static final String UPLOAD_PATH = "/Users/seung7642/neowiz-data/board";

    public static AttachFile uploadFile(MultipartFile multipartFile) throws Exception {
        AttachFile attachFile = new AttachFile();
        String originalFileName = multipartFile.getOriginalFilename(); // 파일명

        // 1. 파일명 중복 방지 처리
        String uuid = getUuid();
        String uploadFileName = getUuidFileName(uuid, originalFileName); // 최종적으로 업로드할 파일명 (uuid + 원본 파일명)

        // 2. 파일 업로드 경로 설정
        String uploadDatePath = getDatePath();                   // 날짜 경로 추출
        File uploadPath = new File(UPLOAD_PATH, uploadDatePath); // 업로드할 기본 경로 + 날짜 경로
        createDatePath(uploadPath);                              // 날짜 경로에 해당하는 디렉터리가 없다면 생성

        // 3. 서버에 파일 저장
        File target = new File(uploadPath, uploadFileName); // 실제 업로드할 경로 + 파일명이 세팅된 파일 객체 생성
        multipartFile.transferTo(target);                   // 파일 저장 !

        // 4. 이미지 파일인 경우 썸네일이미지 생성
        if (checkImageType(target)) {
            createThumbnail(multipartFile, uploadPath, uploadFileName);
            attachFile.setImage(true);
        }

        attachFile.setFileName(originalFileName);
        attachFile.setUploadPath(uploadDatePath);
        attachFile.setUuid(uuid);
        return attachFile;
    }

    public static byte[] displayFile(String fileName) throws IOException {
        File file = new File(UPLOAD_PATH + "/" + fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    /**
     * 한 디렉터리에 너무 많은 파일이 업로드되는 문제를 방지하기 위하여, 년/월/일에 해당하는 하위 디렉터리를 생성하기 위해
     * 오늘 날짜를 기준으로 년/월/일 값을 구한다. ex) 2020 02 08
     *
     * @param
     * @return String
     */
    public static String getDatePath() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        return str.replace("-", File.separator);
    }

    /**
     * 년/월/일 하위 디렉터리 생성 ( 한 디렉터리에 너무 많은 파일이 생성되는 문제 방지 )
     *
     * @param uploadPath
     * @return
     */
    private static void createDatePath(File uploadPath) {
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
    }

    /**
     * 섬네일 이미지를 생성한다.
     *
     * @param uploadPath : 업로드할 디렉터리 경로 ( 년/월/일 포함 )
     * @param uploadFileName : 업로드할 원본 파일명
     * @return String
     */
    private static String createThumbnail(MultipartFile multipartFile, File uploadPath, String uploadFileName) {
        String thumbFileName = "s_" + uploadFileName;

        try (FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, thumbFileName))) {
            Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
        }

        return thumbFileName;
    }

    /**
     * 파라미터로 UUID와 원본 파일명을 받아 업로드에 사용할 파일명을 반환한다.
     *
     * @param uuid
     * @param originalFileName
     * @return String
     */
    private static String getUuidFileName(String uuid, String originalFileName) {
        return uuid + "_" + originalFileName;
    }

    /**
     * 랜덤한 UUID 값을 반환한다.
     *
     * @return String
     */
    private static String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 파일의 MIME 타입으로 이미지 파일인지 확인한다.
     *
     * @param file
     * @return boolean
     */
    private static boolean checkImageType(File file) {
        String mime = getMimeType(file);
        if (!mime.isEmpty()) return mime.contains("image");

        return false;
    }

    /**
     * 파일의 MIME 타입을 가져온다.
     *
     * @param file File
     * @return String
     */
    public static String getMimeType(File file) {
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

    /**
     * 파일의 MIME 타입을 가져온다.
     *
     * @param fileName String
     * @return String
     */
    public static String getMimeType(String fileName) {
        return getMimeType(new File(UPLOAD_PATH + "/" + fileName));
    }
}

