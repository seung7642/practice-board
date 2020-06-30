package com.pangtrue.practice.commons.utils;

import com.nhncorp.lucy.security.xss.LucyXssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;
import com.nhncorp.lucy.security.xss.XssSaxFilter;
import lombok.Getter;
import lombok.Setter;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 16.
 * Time: 오전 10:16
 *
 * XSS 대응 적용 기술은 총 3가지.
 *   1. HttpHeaderSecurityFilter
 *   2. Pebble Escape
 *   3. Lucy XSS Servlet Filter (Lucy XSS Filter)
 */
public class XssFilterUtils {

    @Getter
    @Setter
    private static boolean on = false;

    public static String escape(String value) {
        return XssPreventer.escape(value);
    }

    public static String unescape(String value) {
        return XssPreventer.unescape(value);
    }

    public static String html(String value) {
        LucyXssFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml", true);
        return filter.doFilter(XssPreventer.unescape(value));
    }

    public static String editor(String value) {
        LucyXssFilter filter = XssSaxFilter.getInstance("lucy-xss-sax-editor.xml", true);
        return filter.doFilter(XssPreventer.unescape(value));
    }
}
