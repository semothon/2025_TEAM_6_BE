package org.semothon.survey.core.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "Semothon", description = "Semothon 6팀 Swagger", version = "v1"),
        servers = {
                @Server(url = "https://itsmeweb.store", description = "배포 서버 URL"),
                @Server(url = "http://localhost:8080", description = "로컬 서버 URL")
        }
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components());
    }
}