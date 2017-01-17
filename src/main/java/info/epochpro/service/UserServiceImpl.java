package info.epochpro.service;

import info.epochpro.common.SessionContext;
import info.epochpro.model.User;
import info.epochpro.repository.UserRepository;
import info.epochpro.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by jin on 2016/12/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User insertUser(User user){
        Date now = new Date();
        user.setRegDate(now);
        return userRepository.save(user);
    }

    @Override
    public User selectUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User selectUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public String selectLoginuser(HttpServletRequest request, HttpServletResponse response,User user) {
        Assert.notNull(user.getName(),"登陆用户名不能为空");
        Assert.notNull(user.getPassword(),"登陆密码不能为空");

        User sameNameUser = userRepository.findByName(user.getName());
        if (sameNameUser == null) {
            return "没有这个用户";
        }else{
            if (!user.getPassword().equals(sameNameUser.getPassword())) {
                return "用户名或密码错误";

            }
        }
        SessionContext.setCurrentUser(request,response,sameNameUser);
        return "登陆成功";
    }
}
