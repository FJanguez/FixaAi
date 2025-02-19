package com.fixaai.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("FixaAi API")
                        .description("FixaAi application Rest API containing CRUD functionalities for professionals and condominiums, as well as scheduling and canceling assistance.")
                        .contact(new Contact()
                                .name("BackEnd Team")
                                .email("filipe.janguez@hotmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://fixaai/api/licenca")));
    }
}