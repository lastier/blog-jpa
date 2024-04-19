package com.estsoft.blogjpa.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// profile=(환경설정)
@Configuration
public class SwaggerConfig {

    @Bean //swagger에서 제공해주는 openAPI 빈을 만들어주는 것
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Blog API") // API 제목
                .description("블로그 CRUD API") // API 설명
                .version("1.0.0"); // API 버전
    }
}
