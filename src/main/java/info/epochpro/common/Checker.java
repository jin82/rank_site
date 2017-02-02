package info.epochpro.common;

import info.epochpro.exceptions.ServiceException;

import java.text.MessageFormat;

/**
 * 数据检查类
 * Created by jin on 2017/1/17.
 */
public class Checker {

    public static <T> T notNull(T o,String errorMsg) {
        if (o == null) {
            throw new ServiceException(errorMsg);
        }
        return o;
    }

    public static <T> T notNull(T o,String errorTemp,String... args ) {
        MessageFormat messageFormat = new MessageFormat(errorTemp);
        String msg = messageFormat.format(args);
        return notNull(o, msg);
    }

}
