package com.pangtrue.practice.commons.exception;

import com.pangtrue.practice.commons.constants.RETURN_TP;
import lombok.Getter;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 10:10
 */
public class MessageException extends RuntimeException {

    @Getter
    private RETURN_TP returnTp;

    @Getter
    private int errorCode;

    @Getter
    private String errorMessage;

    public MessageException(RETURN_TP returnTp) {
        super(returnTp.getMessage());
        this.returnTp = returnTp;
    }

    public MessageException(String errorMessage) {
        this(RETURN_TP.FAIL, errorMessage);
    }

    public MessageException(RETURN_TP returnTp, String errorMessage) {
        super(errorMessage);

        returnTp.setMessage(errorMessage);
        this.returnTp = returnTp;
    }

    public MessageException(RETURN_TP returnTp, String errorMessage, Throwable throwable) {
        super(returnTp.getMessage(), throwable);

        returnTp.setMessage(errorMessage);
        this.returnTp = returnTp;
    }
}
