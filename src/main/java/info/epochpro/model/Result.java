package info.epochpro.model;

import info.epochpro.common.enums.TrueOrFalse;
import info.epochpro.exceptions.ServiceException;

import java.io.Serializable;

/**
 * 统一返回数据类
 * Created by jin on 2017/2/2.
 */
public class Result<T> implements Serializable{

    private byte success;

    private Integer code;

    private T data;

    private Result(byte success, Integer code, T data) {
        this.success = success;
        this.code = code;
        this.data = data;
    }

    public static <T> Result success(T data) {
        return new Result<>(TrueOrFalse.TRUE.value(), null, data);
    }

    public static <T extends ServiceException> Result failure(T e) {
        return new Result<>(TrueOrFalse.FALSE.value(),e.getCode(),e.getMessage());
    }

    public static <T> Result failure(T e) {
        return new Result<>(TrueOrFalse.FALSE.value(), -1, e);
    }

    public byte getSuccess() {
        return success;
    }

    public void setSuccess(byte success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
