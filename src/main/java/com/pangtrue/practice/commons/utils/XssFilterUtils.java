package com.pangtrue.practice.commons.utils;

import java.util.Optional;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 16.
 * Time: 오전 10:16
 */
public class XssFilterUtils {

    /**
     * XSS 대응 적용 기술은 총 3가지.
     *   1. HttpHeaderSecurityFilter
     *   2. Pebble Escape,
     *   3. Lucy XSS Servlet Filter (Lucy XSS Filter)
     */

    public Optional<Integer> calculate(int a, int b, Calc calcImpl) {
        Integer result = calcImpl.operate(a, b);

        return Optional.of(result)
                .filter((data) -> data <= 100 ? true : false);
    }
}
