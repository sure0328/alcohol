package com.sinolease.base.exception;

import com.sinolease.base.entity.ErrorCode;

/**
 * Created by AlbertLy on 2016/11/21.
 */
public class UploadException extends Exception {

    public UploadException(String msg) {
        super(ErrorCode.INTERNAL_ERROR + "-" + msg);
    }

    public UploadException(String msg, Throwable cause) {
        super(ErrorCode.INTERNAL_ERROR + "-" + msg, cause);
    }
}
