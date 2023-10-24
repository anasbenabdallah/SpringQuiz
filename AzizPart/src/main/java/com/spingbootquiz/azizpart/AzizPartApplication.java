package com.spingbootquiz.azizpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AzizPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(AzizPartApplication.class, args);
    }

}
