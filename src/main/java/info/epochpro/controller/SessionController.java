package info.epochpro.controller;

import info.epochpro.model.User;
import info.epochpro.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆控制器
 * Created by jin on 2016/12/11.
 */
@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public Map<String, String> login(HttpServletRequest request, HttpServletResponse response,User user) {
        Map<String, String> msg = new HashMap<>();

        String msgValue = userService.selectLoginuser(user);
        msg.put("msg", msgValue);
        return msg;
    }
}
