package info.epochpro.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static info.epochpro.common.Checker.notNull;

/**
 * cookie工具类
 * Created by jin on 2017/1/17.
 */
public class CookieUtils {

    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String key, String value, int maxAge){
        notNull(key, "cookie's key is null");
        Cookie cookie = new Cookie(key, value==null?"":value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(getPath(request));
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static String getPath(HttpServletRequest request) {
        String path = request.getContextPath();
        return (path == null || path.length() == 0) ? "/" : path;
    }


}
