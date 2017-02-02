package info.epochpro.common.helper;

import org.springframework.context.ApplicationContext;

import static info.epochpro.common.Checker.notNull;


/**
 * bean 帮助类
 * Created by jin on 2017/1/17.
 */
public class SpringBeanHelper {

    private static ApplicationContext applicationContext;

    private final static String BEAN_NOT_FOUND = "{0} bean is not found";

    public static void init(ApplicationContext applicationContext) {
        notNull(applicationContext,"applicationContext is null");
        SpringBeanHelper.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> t) {
        T bean = applicationContext.getBean(t);
        return notNull(bean, BEAN_NOT_FOUND, t.getName());
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        Object bean = applicationContext.getBean(name);
        return (T) notNull(bean, BEAN_NOT_FOUND, name);

    }

}
