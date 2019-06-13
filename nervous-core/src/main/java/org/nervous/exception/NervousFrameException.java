package org.nervous.exception;

import org.nervous.exception.base.BaseException;
import org.nervous.exception.base.ErrorCode;
import org.nervous.exception.base.ErrorCodeI;

public class NervousFrameException extends BaseException {

    public NervousFrameException(ErrorCodeI errorCodeI) {
        super(errorCodeI);
    }

    public NervousFrameException(String code, String message) {
        super(new ErrorCode(code, message));
    }


    public NervousFrameException(Throwable cause, ErrorCodeI errorCodeI) {
        super(cause, errorCodeI);
    }
}
