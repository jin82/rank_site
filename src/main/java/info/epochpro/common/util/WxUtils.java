package info.epochpro.common.util;

import info.epochpro.common.WxSign;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 微信组件
 * Created by jin on 2016/12/13.
 */
@Component
public class WxUtils {

    private final Log log = LogFactory.getLog(WxUtils.class);

    @Value("${wx.token}")
    private String token;

    public String wxCheck(WxSign sign){
        String[] s = new String[]{token,sign.getTimestamp(),sign.getNonce()};

        List<String> arrs = Arrays.asList(s);

        Collections.sort(arrs);
        StringBuilder sb = new StringBuilder();
        arrs.forEach(sb::append);
        return EncryptUtils.SHA(sb.toString());
    }
}
