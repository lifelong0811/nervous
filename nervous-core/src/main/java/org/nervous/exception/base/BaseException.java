package org.nervous.exception.base;

import com.google.common.graph.MutableGraph;

/**
 * @author wangleijie
 */
public class BaseException extends RuntimeException{
    private ErrorCodeI errorCodeI;

    public BaseException(ErrorCodeI errorCodeI) {
        super(errorCodeI.getErrorMsg());
        this.errorCodeI = errorCodeI;
    }
    public BaseException(Throwable cause, ErrorCodeI errorCodeI) {
        super(errorCodeI.getErrorMsg(), cause);
        this.errorCodeI = errorCodeI;
    }
}
