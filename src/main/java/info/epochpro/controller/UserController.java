package info.epochpro.controller;

import info.epochpro.model.Result;
import info.epochpro.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static info.epochpro.model.Result.success;

/**
 * 用户控制器
 * Created by jin on 2016/12/11.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @ApiOperation("得到用户信息")
    @GetMapping("/{name}")
    public Result userInfo(@PathVariable("name") String name){
        return success(userService.selectUserByName(name));
    }

}
