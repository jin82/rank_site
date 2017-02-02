package info.epochpro.controller;

import info.epochpro.model.Result;
import info.epochpro.model.Token;
import info.epochpro.model.User;
import info.epochpro.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static info.epochpro.model.Result.success;

/**
 * 网站控制器
 * Created by jin on 2017/2/2.
 */
@RestController
@RequestMapping("/m")
public class SiteController {

    @Autowired
    private UserService userService;

    @RequestMapping("/token")
    public Result token(User user) {
        Token token = userService.token(user);
        return success(token);
    }

    @PostMapping("/sign")
    public Result signin(@Valid User user) {
        return success(userService.insertUser(user));
    }

}
