package com.microservice.blog.configuration;

import feign.Client;
import feign.Logger;
import feign.Request;
import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    public Client feignHttpClient() {
        System.out.println("⚙️ ApacheHttpClient is being used for Feign");
        return new ApacheHttpClient();
    }

    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(3000, 3000); // connectTimeout, readTimeout in ms
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
