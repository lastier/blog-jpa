package com.estsoft.blogjpa.controller;


import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BlogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext ac;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ac).build();
//        blogRepository.deleteAll();
    }

    @DisplayName("블로그 글 추가 성공!")
    @Test
    public void addArticle() throws Exception {
        //given : 저장하고 싶은 블로그 정보
        AddArticleRequest request = new AddArticleRequest("제목", "내용");
        //object->json
        String json = objectMapper.writeValueAsString(request);

        //when : POST /api/articles
        ResultActions resultActions = mockMvc.perform(post("/api/articles")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));

        //then : 저장 확인, HttpStatus 201 검증 {"title" : "제목", "content" : "내용"}
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("title").value(request.getTitle()))
                .andExpect(jsonPath("content").value(request.getContent()));
    }

    @DisplayName("블로그 글 목록 조회")
    @Test
    public void showArticle() throws Exception {
        //given : blogRepository.save
        List<Article> articleList = new ArrayList<>();
        Article article1 = new Article("title1", "content1");
        Article article2 = new Article("title2", "content2");
        articleList.add(article1);
        articleList.add(article2);
        blogRepository.saveAll(articleList);

        //when : GET /api/articles
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));

        //then : Json API 호출 결과, save한 데이터 비교
        // [{"title" : "title1" , "contents" : "contents1"}]
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(articleList.get(0).getTitle())) //List로 저장된 .get(0) 인덱스의 값을 읽어옴
                .andExpect(jsonPath("$[0].content").value(articleList.get(0).getContent()))
                .andExpect(jsonPath("$[1].title").value(articleList.get(1).getTitle()))
                .andExpect(jsonPath("$[1].content").value(articleList.get(1).getContent())
                );
    }

    @DisplayName("블로그 글 삭제 테스트")
    @Test
    public void deleteById() throws Exception{
        //given : 삭제 대상 데이터 저장
        Article article = blogRepository.save(new Article("title", "content"));
        Long id = article.getId();
        System.out.println("id 값 : "+article.getId());

        //when : /api/articles/{id}
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

        //then : DELETE 결과 확인 검증, HttpStatus 200 응답코드 확인
        resultActions.andExpect(status().isOk());

        Optional<Article> deleteArticle = blogRepository.findById(id);
        Assertions.assertFalse(deleteArticle.isPresent());
    }

    //PUT (글 수정)
    @DisplayName("블로그 글 수정 테스트!")
    @Test
    public void updateArticle() throws Exception {
        //given
       Article article = blogRepository.save(new Article("title", "content"));
       Long id = article.getId();
        AddArticleRequest request = new AddArticleRequest("updated title", "updated content");


        //when : PUT /api/articles/{id} json {"title", : "update title", "content" : "update content"}
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", id)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("title").value(request.getTitle()))
                .andExpect(jsonPath("content").value(request.getContent()));

        Article updateArticle = blogRepository.findById(id).orElseThrow();
        assertThat(updateArticle.getTitle()).isEqualTo(request.getTitle());
        assertThat(updateArticle.getContent()).isEqualTo(request.getContent());
    }
}