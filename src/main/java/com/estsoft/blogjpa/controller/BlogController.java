package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Blog CRUD")
@RestController
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @PostMapping("/api/articles") //JSON { "title": "제목", "content" : "내용"}
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request){
        Article article = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article.toResponse());
    }

    @RequestMapping(value = "/api/articles", method = RequestMethod.GET)
    @Operation(summary = "블로그 전체 목록 보기", description = "블로그 메인 화면에서 보여주는 전체 목록")
    @ApiResponses( value = {@ApiResponse(responseCode = "100", description = "요청에 성공했습니다.", content = @Content(mediaType = "application/json"))})
    public ResponseEntity<List<ArticleResponse>> showArticle() {
        List<Article> articleList = blogService.findAll();
        List<ArticleResponse> responseList = articleList.stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/api/articles/{id}")
    @Parameter(name="id", description = "블로그 글 ID", example = "45")
    public ResponseEntity<ArticleResponse> showOneArticle(@PathVariable Long id){
        Article article = blogService.findById(id);

        if(article == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(article.toResponse());
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
        public ResponseEntity<Void> deleteOneArticles(@PathVariable Long id) {
        blogService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody  AddArticleRequest request){
        Article updated = blogService.update(id, request);
//        Article updated = blogService.updateTitle(id, request);
        return ResponseEntity.ok(updated);
    }
}
