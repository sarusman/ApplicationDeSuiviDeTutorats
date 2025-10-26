package ApplicationDeSuiviDeTutorat.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API de Suivi de Tutorat", version = "v1"))
public class OpenApiConfig {
}