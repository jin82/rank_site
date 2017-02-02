package info.epochpro.service;

import info.epochpro.common.Constant;
import info.epochpro.common.enums.ErrorEnum;
import info.epochpro.common.helper.JwtHelper;
import info.epochpro.common.util.UUIDGenerator;
import info.epochpro.exceptions.ServiceException;
import info.epochpro.model.Token;
import info.epochpro.model.User;
import info.epochpro.repository.UserRepository;
import info.epochpro.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import static info.epochpro.common.Checker.notNull;

/**
 * Created by jin on 2016/12/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${epochpro.vister.key}")
    private String key;

    @Override
    public User insertUser(User user){
        if (selectUserByName(user.getName()) != null) {
            throw new ServiceException(ErrorEnum.USERNAME_USED);
        }
        Date now = new Date(System.currentTimeMillis());
        user.setRegDate(now);
        return userRepository.save(user);
    }

    @Override
    public User selectUserById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public User selectUserByName(String name) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new ServiceException(ErrorEnum.USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public User selectLoginuser(User user) {
        notNull(user.getName(),"登陆用户名不能为空");
        notNull(user.getPassword(),"登陆密码不能为空");

        User sameNameUser = userRepository.findByName(user.getName());
        if (sameNameUser == null) {
            throw new ServiceException(ErrorEnum.USER_NOT_FOUND);
        }else{
            if (!user.getPassword().equals(sameNameUser.getPassword())) {
                throw new ServiceException(ErrorEnum.AUTH_ERROR);
            }
        }
        return sameNameUser;
    }

    @Override
    public Token token(User user) {
        User u = selectLoginuser(user);
        String secret = UUIDGenerator.uuid();
        u.setSecret(secret);
        userRepository.save(u);
        String tokenStr = JwtHelper.createJWT(u, Constant.TOKEN_EXPIRES, key);
        Token token = new Token();
        token.setToken(tokenStr);
        token.setExpires(Constant.TOKEN_EXPIRES);
        token.setType("bearer");
        return token;
    }
}
