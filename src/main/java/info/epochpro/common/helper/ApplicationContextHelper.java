package info.epochpro.common.helper;

import info.epochpro.model.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 应用上下文
 * Created by jin on 2017/1/17.
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanHelper.init(applicationContext);
    }

    private final static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public static User currentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}
