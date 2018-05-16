package source.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(Predicates.not(PathSelectors.regex("/docs.*")))
//                .paths(Predicates.not(PathSelectors.regex("/api/user.*")))
                .build()
                .apiInfo(metaData())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(response404.build()))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(response404.build()))
                ;
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for Kasper",
                "1.0",
                "Terms of service",
                new Contact("Abdurakhmon Jamoliddinov", null, null),
                null,
                null);
        return apiInfo;
    }

    ResponseMessageBuilder response404 = new ResponseMessageBuilder()
            .code(404)
            .message("Not Found")
            .responseModel(new ModelRef("Error"));
}
