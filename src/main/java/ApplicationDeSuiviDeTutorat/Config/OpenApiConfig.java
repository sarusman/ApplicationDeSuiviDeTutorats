package ApplicationDeSuiviDeTutorat.Config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI astaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ASTA - API Suivi Tutorat")
                        .description("Documentation de l'API interne ASTA.")
                        .version("v1"));
    }
}
