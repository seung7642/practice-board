package com.pangtrue.practice.commons.utils;

import com.pangtrue.practice.commons.exception.NotValidException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PreconditionUtils {

    public static void invalidCondition(boolean flag, String message) throws NotValidException {
        if (flag) log.debug(message);

        throw new NotValidException();
    }
}