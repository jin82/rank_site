package info.epochpro.common.enums;

/**
 * 异常枚举
 * Created by jin on 2017/2/2.
 */
public enum ErrorEnum {
    USER_NOT_FOUND(1, "用户不存在"),
    AUTH_ERROR(2, "认证失败"),
    USERNAME_USED(3, "用户名已经被使用"),
    ;

    private int code;

    private String text;

    ErrorEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int code() {
        return code;
    }

    public String text() {
        return text;
    }
}
