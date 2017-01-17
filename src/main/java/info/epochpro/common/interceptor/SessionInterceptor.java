package info.epochpro.common.interceptor;

import info.epochpro.common.SessionContext;
import info.epochpro.common.Visitor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session 拦截器
 * Created by jin on 2016/12/11.
 */
@Component("sessionInterceptor")
public class SessionInterceptor extends HandlerInterceptorAdapter{

    private final Log log = LogFactory.getLog(SessionInterceptor.class);

    private final String LOG_URL = "/session";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Visitor visitor = SessionContext.getVisitorFromCookie(request, response);
        if (!visitor.isSuccess()) {
            response.sendRedirect(LOG_URL);
            return false;
        }else{
            SessionContext.setCurrentUser(request,response,visitor);

        }
        return super.preHandle(request, response, handler);
    }
}
