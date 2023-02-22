package com.stackroute;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.context.SecurityContext;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.service.contexts.SecurityContext;


import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

    public static   final String authorization_header="Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT",authorization_header,"header");
    }

    private List<SecurityContext> securityContextList() {

        return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
    }

    private List<SecurityReference> sf(){
        AuthorizationScope scopes=new AuthorizationScope("global","accessEverything");
        return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] {scopes}));
    }
   /* @Bean
    public Docket docket(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }*/

    @Bean
    public Docket api() {



        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContextList())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(metaData()) ;

    }

    private ApiInfo getInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "HomeyCraft Application",
                "*************",
                "Authentication Service Application",
                "Terms of service",
                "abc@gmail.com",
                "License of API",
                "https://swagger.io/docs/");
        return apiInfo;
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Authentication Service")
                .description("------------------\n Authentication service implementation " +
                        "\n It allow us to authenticate the user credentials with the JWT security  " +
                        "\n --------------------------------------------")
                .version("1.1.0")
                .build();
    }

}
