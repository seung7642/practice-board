package com.pangtrue.practice;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.07.04
 * Time: 00:20
 */
public class BCryptTest {

    @Test
    public void bCryptTest() {
        String hashpw = BCrypt.hashpw("password1234", BCrypt.gensalt());
        System.out.println("Hash PW: " + hashpw);

        if (BCrypt.checkpw("password1234", hashpw)) {
            // do somthing...
            System.out.println("Same password.");
        }
    }
}
