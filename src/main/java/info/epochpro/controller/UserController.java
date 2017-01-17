package info.epochpro.controller;

import info.epochpro.model.User;
import info.epochpro.service.inter.UserService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * Created by jin on 2016/12/11.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("注册用户")
    @ApiModelProperty(dataType = "info.epochpro.model.User",allowableValues = "range[1,3]")
    @PostMapping("")
    public User registerUser(User user) {
        return userService.insertUser(user);
    }

//    @ApiOperation("得到用户信息")
    @GetMapping("/{name}")
    public User userInfo(String name){
        return userService.selectUserByName(name);
    }

}
