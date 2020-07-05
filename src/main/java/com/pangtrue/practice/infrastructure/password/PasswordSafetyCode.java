package com.pangtrue.practice.infrastructure.password;

import lombok.Getter;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.07.05
 * Time: 01:00
 */
public enum PasswordSafetyCode {

    SHORT("너무 짧음"),
    WEAK("취약"),
    FAIR("적합"),
    GOOD("보통"),
    STRONG("강력");

    @Getter
    private String comment;

    PasswordSafetyCode(String comment) {
        this.comment = comment;
    }
}
