package com.projetointegrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetointegradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetointegradorApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("321"));
//    }
}
