package info.epochpro.common.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 网站帮助类
 * Created by jin on 2017/2/3.
 */
@Component
public final class ServerHelper {

    @Value("${epochpro.host}")
    private static String host;

    public static String host() {
        return host;
    }
}
