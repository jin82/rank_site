package info.epochpro.common;

import info.epochpro.common.util.CookieUtils;
import info.epochpro.common.util.IPUtils;
import info.epochpro.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static info.epochpro.common.Checker.notNull;

/**
 * session 上下文
 * Created by jin on 2016/12/11.
 */
@Component
public class SessionContext {

    @Value("${epochpro.vister.key}")
    private static String key;

    private static final Log log = LogFactory.getLog(SessionContext.class);

    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    private static final String VISITOR_COOKIE = "VIS_COOKIE";

    private final static int DEFAULT_EXPIRE_SECONDS = 1800;

    public static void setCurrentUser(HttpServletRequest request,HttpServletResponse response,Visitor visitor) {
        CookieUtils.setCookie(request, response, VISITOR_COOKIE, visitor.createValue(), DEFAULT_EXPIRE_SECONDS);
        User user = visitor.createUser();
        USER_THREAD_LOCAL.set(user);
    }

    public static void setCurrentUser(HttpServletRequest request,HttpServletResponse response,User user) {
        Visitor visitor = Visitor.getInstance(key);
        visitor.createVisitor(user);
        visitor.setIp(IPUtils.getClientIp(request));
        visitor.setMac(IPUtils.getMACAddress(visitor.getIp()));
        CookieUtils.setCookie(request, response, VISITOR_COOKIE, visitor.createValue(), DEFAULT_EXPIRE_SECONDS);
        USER_THREAD_LOCAL.set(user);
    }

    public static User getCurrentUser() {
        User user = USER_THREAD_LOCAL.get();
        return notNull(user,"没有当前用的的登陆信息");
    }

    public static void removeCurrentUser() {
        USER_THREAD_LOCAL.remove();
    }

    public static Visitor getVisitorFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtils.getCookie(request, VISITOR_COOKIE);
        if (cookie == null) {
            return Visitor.fail("未获取到登录的用户信息");
        }else{
            String value = cookie.getValue();
            Visitor visitor = Visitor.getInstance(key).createVisitor(value);
            if (!visitor.isSuccess()) {
                return visitor;
            }
            String ip = IPUtils.getClientIp(request);
            String mac = IPUtils.getMACAddress(ip);
            if (!Objects.equals(visitor.getIp(), ip) || !Objects.equals(visitor.getMac(), mac)) {
                return Visitor.fail("登陆用户信息异常");
            }
            CookieUtils.setCookie(request, response, VISITOR_COOKIE, visitor.createValue(), DEFAULT_EXPIRE_SECONDS);
            return visitor;
        }

    }

}
