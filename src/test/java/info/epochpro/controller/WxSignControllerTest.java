package info.epochpro.controller;

import info.epochpro.common.WxSign;
import info.epochpro.common.util.WxUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 微信校验测试
 * Created by jin on 2016/12/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class WxSignControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WxUtils wxUtils;

    @Autowired
    private WxSignController wxSignController;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(wxSignController).build();
    }

    @Test
    public void sign() throws Exception {
        WxSign wxSign = new WxSign();
        wxSign.setEchostr("6410292369901191007");
        wxSign.setTimestamp("1481644372");
        wxSign.setNonce("1124596444");

        String signature = wxUtils.wxCheck(wxSign);
        assertEquals("e186c7044cdcb3f291d4a5f67fe90d7335448321",signature);
        wxSign.setSignature(signature);

        RequestBuilder request ;
        request = get("/wxsign")
                .param("echostr", wxSign.getEchostr())
                .param("timestamp", wxSign.getTimestamp())
                .param("nonce", wxSign.getNonce())
                .param("signature", wxSign.getSignature());

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(wxSign.getEchostr())));

    }

}