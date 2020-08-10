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
 * 아래 사항을 검토하자.
 *   1. XSS 공격이 발생하는 곳(게시판 등)의 성격은 어떠한가 ?
 *   2. lucy-xss-filter의 어떤 필터를 사용할 것인가 ?
 *   3. 필터링 대상은 어떻게 가져갈 것인지 ?
 */
public class XssFilterUtils {

    @Getter
    @Setter
    private static boolean on = false;

    /**
     * HTML의 모든 태그를 무력화시킨 문자열로 변환한다.
     * BASIC_ESCAPE : '\', '&', '<', '>'
     * @param dirty
     * @return String
     */
    public static String escape(String dirty) {
        return XssPreventer.escape(dirty);
    }

    /**
     * 치환된 문자열에서 다시 원본 메시지를 복구한다.
     * @param clean
     * @return String
     */
    public static String unescape(String clean) {
        return XssPreventer.unescape(clean);
    }

    public static String html(String dirty) {
        LucyXssFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml", true);
        return filter.doFilter(XssPreventer.escape(dirty));
    }

    public static String editor(String value) {
        LucyXssFilter filter = XssSaxFilter.getInstance("lucy-xss-sax-editor.xml", true);
        return filter.doFilter(XssPreventer.unescape(value));
    }
}
