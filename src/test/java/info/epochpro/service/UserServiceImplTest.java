package info.epochpro.service;

import info.epochpro.common.util.EncryptUtils;
import info.epochpro.model.Token;
import info.epochpro.model.User;
import info.epochpro.service.inter.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jin on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    public void crudUser() throws Exception {
        User user = insert();
        selectById(user.getUuid());
        selectByName(user.getName());
        del(user.getUuid());
    }

    private User insert() {
        String password = EncryptUtils.getMD5("123456");

        User user = new User();
        user.setName("king");
        user.setNickname("浙江吴彦祖");
        user.setPassword(password);
        user = userService.insertUser(user);
        assertNotNull("测试新增用户出错", user.getUuid());
        return user;
    }

    public User selectById(String uuid) {
        User dbUser = userService.selectUserById(uuid);
        assertNotNull("通过ID查询用户出错", dbUser);
        return dbUser;
    }

    public User selectByName(String name) {
        User dbUserName = userService.selectUserByName(name);
        assertNotNull("通过用户名查询用户出错", dbUserName);
        return dbUserName;
    }

    public void del(String uuid) {
        userService.delUser(uuid);
    }

    @Test
    public void token() {
        User user = insert();
        Token token = userService.token(user);
        assertNotNull("token 获取失败", token);
        System.out.println(token);
    }


}