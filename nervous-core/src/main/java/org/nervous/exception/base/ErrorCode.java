package org.nervous.exception.base;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * @author wangleijie
 */
@Setter
@AllArgsConstructor
public class ErrorCode implements ErrorCodeI{

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
