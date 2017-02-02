package info.epochpro.exceptions;

import info.epochpro.common.enums.ErrorEnum;

/**
 * 业务异常
 * Created by jin on 2016/12/15.
 */
public class ServiceException extends RuntimeException {

    private int code;

    /**
     * 业务的异常
     * @param e 异常
     */
    public ServiceException(ErrorEnum e) {
        super(e.text());
        this.code = e.code();
    }

    /**
     * 自定义的系统异常
     * @param text 描述
     */
    public ServiceException(String text) {
        super(text);
        this.code = 0;
    }

    public int getCode() {
        return code;
    }

}
