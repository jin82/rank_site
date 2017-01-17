package info.epochpro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * mvc配置
 * Created by jin on 2016/12/11.
 */
@Configuration
@Profile("dev")
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private HandlerInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/user/**");
        super.addInterceptors(registry);
    }
}
