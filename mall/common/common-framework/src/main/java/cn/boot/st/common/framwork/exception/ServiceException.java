package cn.boot.st.common.framwork.exception;

import cn.boot.st.common.framwork.base.ResponseCode;


public final class ServiceException extends RuntimeException {
    private ResponseCode code;

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(ResponseCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public ServiceException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }
}
