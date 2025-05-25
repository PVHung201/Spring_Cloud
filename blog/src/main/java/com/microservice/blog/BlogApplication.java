package com.microservice.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BlogApplication {

    public static void main(String[] args) {
        System.out.println(System.getenv());
        SpringApplication.run(BlogApplication.class, args);
    }

}
