package info.epochpro.common.util;

import java.util.UUID;

/**
 * UUID 生成器
 * Created by jin on 2016/12/16.
 */
public class UUIDGenerator {

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
