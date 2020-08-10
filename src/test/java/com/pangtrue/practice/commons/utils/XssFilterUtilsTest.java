package com.pangtrue.practice.commons.utils;

import com.nhncorp.lucy.security.xss.XssFilter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.08.10
 * Time: 15:00
 */
public class XssFilterUtilsTest {

    XssFilter filter = XssFilter.getInstance();

    @Test
    public void XSS_필터링_검증() throws Exception {
        String dirty = "<script></script>";
        String clean = filter.doFilter(dirty);
        String expected = "&lt;script&gt;&lt;/script&gt;"; // 예상 문자열

        assertEquals(expected, clean);
    }
}
