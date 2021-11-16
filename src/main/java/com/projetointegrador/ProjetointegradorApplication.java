package com.projetointegrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ProjetointegradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetointegradorApplication.class, args);
    }
}