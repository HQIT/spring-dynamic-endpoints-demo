package com.cloume.fkb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "APIs",
        version = "1.0",
        description = "Documentation APIs v1.0"))
public class DynamicEndpointsDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicEndpointsDemoApplication.class, args);
    }
}
