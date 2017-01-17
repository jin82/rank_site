package info.epochpro.service.inter;

import info.epochpro.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jin on 2016/12/11.
 */
public interface UserService {
    User insertUser(User user);

    User selectUserById(Long id);

    User selectUserByName(String name);

    String selectLoginuser(HttpServletRequest request, HttpServletResponse response,User user);
}
