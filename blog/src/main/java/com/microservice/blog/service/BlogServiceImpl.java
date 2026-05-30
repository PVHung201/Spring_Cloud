package com.microservice.blog.service;

import com.microservice.blog.helper.PdfHelper;
import com.microservice.blog.helper.ResourcePdf;

import com.microservice.blog.model.Author;
import com.microservice.blog.model.Blog;
import com.microservice.blog.model.BlogAuthorResponse;
import com.microservice.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.resource.HttpResource;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final PdfHelper pdfHelper;
    private final BlogRepository repository;

    @Override
    public Blog getBlog(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BlogAuthorResponse getDataResponse(Author author, Blog blog) {
        return new BlogAuthorResponse(author, blog);
    }

    @Override
    public boolean changeConfiguration() {
        return true;
    }

    @Override
    public HttpResource printDocument(String id) {
        String template = getTemplatePath();

        Map<String, Object> mydata = getMyData();

        byte[] bytes = pdfHelper.generatePdf(template, mydata);

        if (ObjectUtils.isEmpty(bytes)) {
            throw new RuntimeException("Gen pdf error");
        }

        String fileName = "lemon.pdf";

        return new ResourcePdf(fileName, bytes);
    }

    private Map<String, Object> getMyData() {
        Map<String, Object> data = new HashMap<>();

        data.put("customerName", "Nguyễn Văn A");
        data.put("customerAddress", "123 Nguyễn Trãi, Hà Nội");
        data.put("IdNumber", "001234567890");
        data.put("PassportNumber", "C1234567");

        data.put("netAmount", "100,000,000 VND");
        data.put("netAmountText", "Một trăm triệu đồng");
        data.put("netRate", "25,500 VND/USD");

        data.put("dayNow", "30");
        data.put("monthNow", "05");
        data.put("yearNow", "2026");

        return data;
    }

    private String getTemplatePath() {
        return "templates/document.html";
    }


}
