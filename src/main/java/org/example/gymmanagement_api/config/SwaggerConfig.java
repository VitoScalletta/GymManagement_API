package org.example.gymmanagement_api.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Gym Management API", version = "1.0", description = "Spor Salonu Yönetim Sistemi API"),
        security = @SecurityRequirement(name = "bearerAuth") // Tüm API'ye kilit simgesi koyar
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Giriş yaptıktan sonra aldığınız token'ı buraya yapıştırın.",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
