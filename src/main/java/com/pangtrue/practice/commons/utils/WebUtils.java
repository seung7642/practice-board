package com.pangtrue.practice.commons.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.pangtrue.practice.commons.constants.Encodings;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.06.26
 * Time: 12:25
 */
@Slf4j
@UtilityClass
public class WebUtils {

    public static final String CONTEXT_PATH_SEPARATOR = "/";

    public static List<String> REQUEST_REMOTE_ADDR_HEADER_NAMES = Lists.newArrayList(
            "X-Forwarded-For",
            "X-Citrix-For",
            "X-RealIP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"
    );

    /**
     * 서버의 호스트 정보를 반환한다.
     */
    public static String getLocalHostInfo() {
        StringBuilder sbLocalHostInfo = new StringBuilder();

        try {
            InetAddress inet = Inet4Address.getLocalHost();
            sbLocalHostInfo.append(inet.getHostName()).append(inet.getHostAddress());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        return sbLocalHostInfo.toString();
    }

    /**
     * request 요청을 보낸 서버의 HostName을 반환한다.
     */
    public static String getHostName(HttpServletRequest request) {
        return request.getRemoteHost();
    }

    /**
     * request 요청을 보낸 서버의 HostAddress를 반환한다.
     */
    public static String getHostAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    /**
     * 요청한 Request 서버 주소를 반환한다.
     * for example, https://dev.pangtrue.com/
     */
    public static String getRequestServer(HttpServletRequest request) {
        StringBuilder sbRequestData = new StringBuilder();
        sbRequestData.append(request.getScheme()).append("://").append(request.getServerName());

        // HTTP | HTTPS 프로토콜이 아니라면 포트 번호도 기입한다.
        if (80 != request.getServerPort() && 443 != request.getServerPort()) {
            sbRequestData.append(":").append(request.getServerPort());
        }
        sbRequestData.append(request.getContextPath());

        return sbRequestData.toString();
    }

    /**
     * 요청한 Request 서버 URL을 반환한다.
     * for example, https://dev.pangtrue.com/board/list/
     */
    public static String getRequestServerUrl(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return new StringBuilder(getRequestServer(request)).append(requestUri).toString();
    }

    /**
     * 요청한 Request 서버 URI를 반환한다.
     * for example, /board/list/ + [Query String]
     */
    public static String getRequestServerUri(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        StringBuilder sbRequestData = new StringBuilder(getRequestServer(request));
        sbRequestData.append(requestUri);

        return getQueryString(request, sbRequestData).toString();
    }

    /**
     * 요청한 Request의 Query String을 반환한다.
     */
    public static StringBuilder getQueryString(HttpServletRequest request, StringBuilder requestData, boolean isWithoutPage) {
        Enumeration<String> names = request.getParameterNames();

        int cnt = 0;
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();

            if (Boolean.TRUE.equals(isWithoutPage)) {
                if ("page".equalsIgnoreCase(name)) {
                    continue;
                }
            }

            if ("rUrl".equals(name)) {
                continue;
            }

            requestData.append(((0 == cnt++) ? "?" : "&"));

            String[] values = request.getParameterValues(name);
            if (ArrayUtils.isNotEmpty(values)) {
                for (int i = 0; i < values.length; i++) {
                    if (i > 0) {
                        requestData.append("&");
                    }

                    requestData.append(name);
                    requestData.append("=");

                    try {
                        requestData.append(URLEncoder.encode(Strings.nullToEmpty(values[i]), Encodings.UTF_8.getValue()));
                    } catch (UnsupportedEncodingException ex) {
                        log.error(ex.getMessage(), ex);
                    }
                }
            }
        }

        return requestData;
    }

    public static StringBuilder getQueryString(HttpServletRequest request, StringBuilder requestData) {
        return getQueryString(request, requestData, false);
    }
}
