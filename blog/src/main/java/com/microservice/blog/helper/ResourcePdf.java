package com.microservice.blog.helper;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.HttpResource;

public class ResourcePdf extends ByteArrayResource implements HttpResource {
    private final String fileName;

    public ResourcePdf(String fileName, byte[] bytes) {
        super(bytes);
        this.fileName = fileName;
    }

    @Override
    @NotNull
    public HttpHeaders getResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentLength(this.getByteArray().length);

        return headers;
    }
}
