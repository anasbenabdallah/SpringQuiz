package com.spingbootcours.Tahapart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TahaPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(TahaPartApplication.class, args);
    }

}
