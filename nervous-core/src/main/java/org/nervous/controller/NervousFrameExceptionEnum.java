package org.nervous.controller;

import org.nervous.exception.base.ErrorCodeI;

public enum NervousFrameExceptionEnum implements ErrorCodeI {
    CLASS_NO_METHOD_FOUND("1001", "在class中没有发现任何函数"),
    REQUEST_BUILDER_CANNOT_FOUND("1002", "用户指定了request build方法，但是未找到"),
    BUILD_METHOD_CHECK_ERROR("1003", "BuildMethod Check错误"),


    ;

    private NervousFrameExceptionEnum(String code, String message) {
        this.errorCode = code;
        this.errorMsg = message;
    }


    private String errorCode;
    private String errorMsg;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
