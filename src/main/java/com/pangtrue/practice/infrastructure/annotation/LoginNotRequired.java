package com.pangtrue.practice.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.07.05
 * Time: 00:40
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LoginNotRequired {
}
