package com.pangtrue.practice.commons.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.06.26
 * Time: 15:25
 */
public enum Encodings {

    UTF_8("UTF-8"),
    EUC_KR("EUC-KR");

    @Getter
    private final String value;

    Encodings(String value) {
        this.value = value;
    }

    public static Optional<Encodings> find(String name) {
        return Arrays.stream(Encodings.values())
                .filter((s -> s.name().equalsIgnoreCase(name)))
                .findAny();
    }
}
