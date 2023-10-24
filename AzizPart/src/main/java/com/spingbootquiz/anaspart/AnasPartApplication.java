package com.spingbootquiz.anaspart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AnasPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnasPartApplication.class, args);
    }

}
