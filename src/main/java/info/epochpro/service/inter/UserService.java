package info.epochpro.service.inter;

import info.epochpro.model.Token;
import info.epochpro.model.User;

/**
 * Created by jin on 2016/12/11.
 */
public interface UserService {
    User insertUser(User user);

    User selectUserById(String id);

    User selectUserByName(String name);

    void delUser(String id);

    User selectLoginuser(User user);

    Token token(User user);
}
