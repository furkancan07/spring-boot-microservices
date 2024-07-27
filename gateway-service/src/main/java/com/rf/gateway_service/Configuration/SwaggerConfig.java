package com.rf.gateway_service.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Mikroservislerin URL'lerini ve path'lerini tanımlayın
    private static final List<String> services = List.of(
            "http://localhost:5861",
            "http://localhost:5860",
            "http://localhost:5862");

    @Bean
    public OpenAPI customOpenAPI() {
        Paths paths = new Paths();

        // Her bir servisin API dokümanını al ve birleşik bir yapıya ekle
        for (String serviceUrl : services) {
            try {
                String url = serviceUrl + "/v3/api-docs";
                String apiDocs = webClientBuilder.build()
                        .get()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                if (apiDocs != null) {

                    OpenAPI serviceOpenAPI = io.swagger.v3.core.util.Json.mapper().readValue(apiDocs, OpenAPI.class);


                    paths.putAll(serviceOpenAPI.getPaths());
                }
            } catch (Exception e) {
                System.err.println("API Docs not available for: " + serviceUrl);
            }
        }

        return new OpenAPI()
                .info(new Info()
                        .title("API Gateway")
                        .version("1.0")
                        .description("Consolidated API Documentation for All Services"))
                .paths(paths);
    }
}
