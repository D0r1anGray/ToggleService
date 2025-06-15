package org.gaenkov;

import org.gaenkov.toggle.service.ToggleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToggleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToggleServiceApplication.class, args);
    }
}