package info.epochpro.common.enums;

/**
 * 是否删除枚举 1-正常 2-删除
 * Created by jin on 2016/12/15.
 */
public class DeleteEnum {

    private byte value;

    private String text;

    DeleteEnum(byte value, String text) {
        this.value = value;
        this.text = text;
    }

}
