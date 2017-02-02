package info.epochpro.common.interceptor;

import info.epochpro.common.enums.ErrorEnum;
import info.epochpro.common.helper.JwtHelper;
import info.epochpro.exceptions.ServiceException;
import info.epochpro.model.User;
import info.epochpro.service.inter.UserService;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static info.epochpro.common.helper.ApplicationContextHelper.setCurrentUser;

/**
 * Token 拦截器
 * Created by jin on 2017/2/2.
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private final Log log = LogFactory.getLog(this.getClass());

    @Value("${epochpro.vister.key}")
    private String key;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");
        if ((auth != null) && (auth.length() > 7)) {
            String HeadStr = auth.substring(0, 6).toLowerCase();
            if (HeadStr.compareTo("bearer") == 0){

                auth = auth.substring(7, auth.length());
                Claims claims = JwtHelper.parseJWT(auth, key);
                if (claims != null){
                    String uuid = claims.get("uuid", String.class);
                    String secret = claims.get("secret", String.class);
                    User user = userService.selectUserById(uuid);
                    if (user == null) {
                        throw new ServiceException(ErrorEnum.USER_NOT_FOUND);
                    }
                    if (Objects.equals(user.getSecret(), secret)) {
                        setCurrentUser(user);
                        return super.preHandle(request, response, handler);
                    }
                }
            }
        }
        log.error("token 为" + auth);
        throw new ServiceException(ErrorEnum.AUTH_ERROR);
    }
}
