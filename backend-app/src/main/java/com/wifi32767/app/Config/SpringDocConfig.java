package com.wifi32767.app.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI selfOpenAPI(@Qualifier("permissionAnnotationCustomizer") OperationCustomizer permissionCustomizer) {
        return new OpenAPI().info(new Info()
                .title("API文档")
                .description("船舶制造业数字化转型API文档")
                .version("v1.0.0"))
//                .externalDocs(new ExternalDocumentation()
//                        .description("更多文档")
//                        .url("https://springdoc.org"))
                ;
    }

}