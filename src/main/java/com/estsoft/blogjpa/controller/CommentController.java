package com.estsoft.blogjpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @PostMapping("/comments/{articleId}")

    @GetMapping("/comments/{articleId}/{commentId}")
}
