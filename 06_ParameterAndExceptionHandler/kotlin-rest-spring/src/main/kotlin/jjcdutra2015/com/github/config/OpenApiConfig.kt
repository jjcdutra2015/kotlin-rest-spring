package jjcdutra2015.com.github.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("RESTful API with Kotlin and Spring Boot")
                    .version("v1")
                    .description("Some description about your AP.")
                    .termsOfService("https://github.com/jjcdutra2015")
                    .license(
                        License().name("Apache 2.0")
                            .url("https://github.com/jjcdutra2015")
                    )
            )
    }
}