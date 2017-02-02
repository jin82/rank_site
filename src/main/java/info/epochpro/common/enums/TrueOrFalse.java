package info.epochpro.common.enums;

/**
 * 是否正确
 * Created by jin on 2017/2/2.
 */
public enum TrueOrFalse{

    TRUE((byte)1, "正确"),
    FALSE((byte)2,"错误"),
    ;

    private byte value;

    private String text;

    TrueOrFalse(byte value, String text) {
        this.value = value;
        this.text = text;
    }

    public byte value() {
        return value;
    }

}
