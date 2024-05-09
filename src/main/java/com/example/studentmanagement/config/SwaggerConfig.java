package com.example.studentmanagement.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the OpenAPI documentation using Swagger for the Student Management System.
 * This class sets up the metadata for the API documentation, such as the title, description, version,
 * and the license under which the API is released.
 *
 * <p>The OpenAPI object created here can be used by tools like Swagger UI to generate visual documentation
 * that helps developers interact with the API through an intuitive interface. It improves API visibility
 * and encourages proper usage.</p>
 *
 * @author Yuhe Chen
 * date: May 9th 2024
 */
@Configuration
public class SwaggerConfig {
    /**
     * Creates an {@link OpenAPI} instance configured with metadata about the API including its title,
     * description, version, and licensing information.
     *
     * <p>This method sets up the API documentation which can be accessed via tools like Swagger UI.
     * The API's overall information is encapsulated in this configuration, making it easy for both
     * developers and users to understand what the API offers and how it should be used.</p>
     *
     * @return the configured {@link OpenAPI} instance ready to be used for generating API documentation
     */
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("StudentManagement")
                .description("StudentManagementSystem")
                .version("1.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation());
    }
}