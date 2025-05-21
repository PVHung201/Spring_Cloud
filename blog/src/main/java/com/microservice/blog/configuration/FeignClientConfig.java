package com.microservice.blog.configuration;

import com.microservice.blog.handle.ErrorHandle;
import com.microservice.blog.handle.TimeoutHandle;
import feign.Client;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;
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
        return new Request.Options(3000, 3000);
    }
//
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorHandle();
    }

    @Bean
    public Retryer retryer() {
//        return new Retryer.Default(1000, 1000, 3);
        return new TimeoutHandle();
    }
}
