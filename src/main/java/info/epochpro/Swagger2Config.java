package info.epochpro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

/**
 * swagger2 配置类
 * Created by jin on 2016/12/11.
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("info.epochpro.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Date.class,Long.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("rank API")
                .description("rank 的api信息")
                .termsOfServiceUrl("http://www.epochpro.info")
                .build();
    }
}
