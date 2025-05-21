package com.microservice.blog.handle;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;

public class ErrorHandle implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 400 -> {
                System.out.println("Please check your URL again ");
                yield new BadRequestException();
            }
            case 404 -> new BadRequestException("Page not found");
            case 500 -> new Exception("Something went wrong while connecting");
            default -> new Exception("Generic error");
        };
    }
}
