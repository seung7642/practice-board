package com.practice.board.commons.utils;

import com.practice.board.application.board.domain.AttachFile;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * User: SeungHo Lee (seung7642@neowiz.com)
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

    private static final String UPLOAD_DIRECTORY = "/board"; // TODO: 앞에 root context 경로를 추가해줘야함

    // 파일 업로드 처리
    public static AttachFile uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        AttachFile attachFile = new AttachFile();
        String originalFileName = multipartFile.getOriginalFilename(); // 파일명

        // 1. 파일명 중복 방지 처리
        String uuid = getUuid();
        String uuidFileName = getUuidFileName(uuid, originalFileName);

        // TODO : 파일 저장 경로를 /resources/static/board/2020/02/08 처럼 하려면?
        // 2. 파일 업로드 경로 설정
        String rootPath = getRootPath(originalFileName, request); // 기본경로 추출
        String datePath = getDatePath(rootPath); // 날짜 경로 추출 && 날짜 폴더 생성

        // 3. 서버에 파일 저장
        File target = new File(rootPath + datePath, uuidFileName); // 파일 객체 생성
        multipartFile.transferTo(target);

        // 4. 이미지 파일인 경우 썸네일이미지 생성
        if (checkImageType(target)) {
            createThumbnail(multipartFile, rootPath + datePath, uuidFileName);
            attachFile.setImage(true);
        }

        attachFile.setFileName(originalFileName);
        attachFile.setUploadPath(rootPath + datePath);
        attachFile.setUuid(uuid);
        return attachFile;
    }

    // 기본 경로 추출
    public static String getRootPath(String fileName, HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath(UPLOAD_DIRECTORY); // 일반파일 경로
    }

    /**
     * 한 디렉터리에 너무 많은 파일이 업로드되는 문제를 방지하기 위하여, 년/월/일에 해당하는 하위 디렉터리를 생성하기 위해
     * 오늘 날짜를 기준으로 년/월/일 값을 구한다. ex) 2020 02 08
     *
     * @param rootPath
     * @return String
     */
    private static String getDatePath(String rootPath) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        str = str.replace("-", File.separator);
        createDateDirectory(rootPath + str);
        return str;
    }

    /**
     * 년/월/일 하위 디렉터리 생성 ( 한 디렉터리에 너무 많은 파일이 생성되는 문제 방지 )
     *
     * @param datePath
     * @return
     */
    private static void createDateDirectory(String datePath) {
//        log.info("업로드할 년/월/일 디렉터리 경로: {}" + datePath);
        File uploadPath = new File(UPLOAD_DIRECTORY, datePath);
        if (!uploadPath.exists()) uploadPath.mkdirs();
    }

    /**
     * 섬네일 이미지를 생성한다.
     *
     * @param uploadPath : 업로드할 디렉터리 경로 ( 년/월/일 포함 )
     * @param uploadFileName : 업로드할 원본 파일명
     * @return String
     */
    private static String createThumbnail(MultipartFile multipartFile, String uploadPath, String uploadFileName) {
        String thumbFileName = "s_" + uploadFileName;

        try (FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, thumbFileName))) {
            Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
        } catch (IOException e) {
//            log.debug(e.getMessage(), e);
        }

        return thumbFileName;
    }

    private static String getUuidFileName(String uuid, String originalFileName) {
        return uuid + "_" + originalFileName;
    }

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
     * @param file
     * @return String
     */
    private static String getMimeType(File file) {
        try {
            return Magic.getMagicMatch(file, false).getMimeType();
        } catch (MagicParseException e) {
//            log.debug(e.getMessage(), e);
        } catch (MagicMatchNotFoundException e) {
//            log.debug(e.getMessage(), e);
        } catch (MagicException e) {
//            log.debug(e.getMessage(), e);
        }
        return "";
    }
}
