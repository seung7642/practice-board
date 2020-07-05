package com.pangtrue.practice.infrastructure.password;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.07.05
 * Time: 01:00
 */
@Data
@AllArgsConstructor
public class PasswordSafetyResponse {

    private PasswordSafetyCode code;

    public static PasswordSafetyResponse of(PasswordSafetyCode code) {
        return new PasswordSafetyResponse(code);
    }
}
