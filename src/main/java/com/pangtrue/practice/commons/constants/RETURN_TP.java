package com.pangtrue.practice.commons.constants;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Optional;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 9:50
 */
public enum RETURN_TP {
    OK("Success !"),
    FAIL("Fail !");

    @Setter
    @Getter
    private String message;

    RETURN_TP(String message) {
        this.message = message;
    }

    public static Optional<RETURN_TP> find(String name) {
        return Arrays.stream(RETURN_TP.values())
                .filter(s -> s.name().equalsIgnoreCase(name))
                .findAny();
    }
}
