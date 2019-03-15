package etu.uportal.spring;

import com.fasterxml.classmate.TypeResolver;
import etu.uportal.rest.UserRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {
        UserRestController.class
})
public class SwaggerConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Upotral API")
                .description("API, позволяющее другим разработчикам взаимодействовать с системой")
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("android")
                .clientSecret("2secret2")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }


    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("etu.uportal.rest"))
                .build()
                .apiInfo(apiInfo())
                .produces(Collections.singleton("application/json"))
                .consumes(Collections.singleton("application/json"))
                .securitySchemes(Collections.singletonList(apiKey()))
                .tags(new Tag("User", "Все что связано с пользователями"))
                .tags(new Tag("Author", "Все что связано с авторами"));
    }

}