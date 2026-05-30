package com.microservice.blog.configuration;

import com.github.jknack.handlebars.Handlebars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HanderBarConfig {
    @Bean
    public Handlebars handlebars() {
        return new Handlebars();
    }
}
