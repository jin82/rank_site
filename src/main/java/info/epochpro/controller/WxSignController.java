package info.epochpro.controller;

import info.epochpro.common.WxSign;
import info.epochpro.common.util.WxUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信验证控制器
 * Created by jin on 2016/12/13.
 */
@RestController
@RequestMapping("/wxsign")
public class WxSignController {

    private final Log log = LogFactory.getLog(WxSignController.class);

    @Autowired
    private WxUtils wxUtils;

    @GetMapping
    public String sign(WxSign wxSign) {


        String rightSignature = wxUtils.wxCheck(wxSign);
        if(rightSignature.equals(wxSign.getSignature())){
            return wxSign.getEchostr();
        }else{
            log.error("微信token请求参数为："+wxSign);
            return wxSign.toString();
        }

    }

}
