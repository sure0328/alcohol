package com.sinolease.base.exception;

import com.sinolease.base.entity.ErrorCode;

/**
 * Created by AlbertLy on 2016/11/21.
 */
public class ExcelException extends Exception {

    public ExcelException(String msg) {
        super(ErrorCode.INTERNAL_ERROR + "-" + msg);
    }

    public ExcelException(String msg, Throwable cause) {
        super(ErrorCode.INTERNAL_ERROR + "-" + msg, cause);
    }
}
