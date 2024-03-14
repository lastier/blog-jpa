package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.external.ExternalApiParser;
import com.estsoft.blogjpa.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonParseController {
    private final BlogService blogService;

    public JsonParseController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping("/api/test")
    public List<Article> test(){
        return blogService.saveBulkArticles();
    }
}
