import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Docket UserApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                //.apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("ru.ds.education.currency.controller"))
                .build()
                .useDefaultResponseMessages(false);
    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Приложение для обучения")
                .description("REST service Spring/Boot")
                .license("лицензия")
                .licenseUrl("https://mail.ru")
                .version(getClass().getPackage().getImplementationVersion())
                .build();
    }
}