package com.thoen.demoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApiApplication.class, args);
        System.out.println("Hello World!");
    }

}
