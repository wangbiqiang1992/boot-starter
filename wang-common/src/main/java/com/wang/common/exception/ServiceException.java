package com.wang.common.exception;

import java.util.UUID;

public class ServiceException extends Exception {

    private static final long serialVersionUID = -7061384322569340647L;

    private String errorCode;

    private String traceId;

    private Throwable cause;

    public ServiceException(String errorCode, String errorMsg) {
        this(errorCode, errorMsg, null, (Throwable) null);
    }

    private ServiceException(String errorCode, String errorMsg, String traceId, Throwable cause) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.traceId = traceId;
        this.cause = cause;
        if (this.traceId == null) {
            this.traceId = UUID.randomUUID().toString().replaceAll("-", "");
        }
    }
}
