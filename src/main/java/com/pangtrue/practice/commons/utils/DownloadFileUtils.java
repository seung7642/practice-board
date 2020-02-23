package com.pangtrue.practice.commons.utils;

import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 2. 6.
 * Time: 오전 9:50
 */
public class DownloadFileUtils {

    private static final String UPLOAD_PATH = "/Users/seung7642/neowiz-data/board";

    public static String getDownloadName(String userAgent, String resourceName) throws UnsupportedEncodingException {
        String downloadName = null;

        if (userAgent.contains("Trident")) { // IE browser
            downloadName = URLEncoder.encode(resourceName, "UTF-8");
        } else if (userAgent.contains("Edge")) { // Edge browser
            downloadName = URLEncoder.encode(resourceName, "UTF-8");
        } else { // Chrome browser
            downloadName = new String(resourceName.getBytes("UTF-8"), "ISO-8859-1");
        }

        return downloadName;
    }

    public static HttpHeaders getHttpHeaders(String[] headerName, String[] headerValue) {
        HttpHeaders headers = new HttpHeaders();

        for (int i = 0; i<headerName.length; ++i) {
            headers.add(headerName[i], headerValue[i]);
        }
        return headers;
    }
}