package com.pangtrue.practice.commons.utils;

import com.pangtrue.practice.commons.exception.NotValidException;
import lombok.extern.slf4j.Slf4j;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Slf4j
public class PreconditionUtils {

    public static void invalidCondition(boolean flag, String message) throws NotValidException {
        if (flag) log.debug(message);

        throw new NotValidException();
    }
}