package info.epochpro.service;

import info.epochpro.common.util.EncryptUtils;
import info.epochpro.model.User;
import info.epochpro.service.inter.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jin on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    @Rollback
    public void crudUser() throws Exception {
        String password = EncryptUtils.getMD5("123456");
        User user = new User("king333","浙江吴彦祖",password,new Date(),null,"111");

        user = userService.insertUser(user);
        assertNotNull("测试新增用户出错", user.getId());


        User dbUser = userService.selectUserById(user.getId());
        assertNotNull("通过ID查询用户出错", dbUser);

        User dbUserName = userService.selectUserByName(user.getName());
        assertNotNull("通过用户名查询用户出错", dbUserName);




    }


}