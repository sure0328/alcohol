package com.sinolease.base.exception;

import com.sinolease.base.entity.ErrorCode;

/**
 * Created by AlbertLy on 2016/12/2.
 */
public class FormCommitException extends Exception{

    public FormCommitException(String msg) {
        super(msg);
    }

    public FormCommitException(String msg, Throwable cause) {
        super(ErrorCode.INTERNAL_ERROR + "-" + msg, cause);
    }

}
