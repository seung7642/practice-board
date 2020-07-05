package com.pangtrue.practice.infrastructure.password;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.07.05
 * Time: 00:55
 */
@Slf4j
@Component
public class PasswordSafetyChecker {

    private final static int PASSWORD_CHARACTERS_NUMBER_MINIMUM = 8;
    private final static int PASSWORD_CHARACTERS_NUMBER_MAXIMUM = 32;

    private final static String PASSWORD_CHARACTERS_DIGITCASE = "0123456789";
    private final static String PASSWORD_CHARACTERS_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private final static String PASSWORD_CHARACTERS_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String PASSWORD_CHARACTERS_SPECAILCHAR = "`~!@#$%^&*()-_=+\\|]}[{'\";:/?.>,<";
    private final static String PASSWORD_CHARACTERS_ALLOW = PASSWORD_CHARACTERS_DIGITCASE
                                                          + PASSWORD_CHARACTERS_LOWERCASE
                                                          + PASSWORD_CHARACTERS_UPPERCASE
                                                          + PASSWORD_CHARACTERS_SPECAILCHAR;

    private final static PasswordSafetyCode[] PASSWORD_CODES = {
            PasswordSafetyCode.WEAK,
            PasswordSafetyCode.FAIR,
            PasswordSafetyCode.GOOD,
            PasswordSafetyCode.STRONG
    };

    /**
     * 패스워드의 안정성을 검증한다.
     * @param password
     * @return PasswordSafetyResponse
     */
    public PasswordSafetyResponse verify(String password) {
        // TODO: 패스워드 검증 로직 작성.

        return PasswordSafetyResponse.of(PASSWORD_CODES[0]);
    }
}
