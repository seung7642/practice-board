package com.pangtrue.practice.infrastructure.entity;

import com.pangtrue.practice.commons.constants.RETURN_TP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.07.01
 * Time: 20:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ResponseBase<T> {

    protected RETURN_TP code = RETURN_TP.FAIL;
    protected String message = "";
    protected T data;
}
