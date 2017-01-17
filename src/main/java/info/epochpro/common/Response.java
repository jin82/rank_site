package info.epochpro.common;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * 返回信息实体
 * Created by jin on 2016/12/15.
 */
public class Response<T> implements Serializable {

    private boolean success;

    private T message;

    public Response(boolean success, T message) {
        this.success = success;
        this.message = message;
    }

    public Response(T message) {
        this.success = true;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("success", success)
                .add("message", message)
                .toString();
    }
}
