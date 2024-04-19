package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.Article;
import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class BlogControllerTest2 {
    @Mock
    BlogService blogService;

    @InjectMocks
    BlogController blogController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    @DisplayName("블로그 정보 추가 - POST api/articles")
    @Test
    void testAddArticle() throws Exception {
        //given
        AddArticleRequest request = new AddArticleRequest("Title", "Content");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        doReturn(new Article(request.getTitle(), request.getContent()))
                .when(blogService).save(any(AddArticleRequest.class)); //any 어떤 객체가 들어가도 상관없다.

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/articles")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON));

        //then: verify로 검증가능
        resultActions.andExpect(status().isCreated())
               .andExpect(jsonPath("title").value(request.getTitle()))
               .andExpect(jsonPath("content").value(request.getContent()));

        Mockito.verify(blogService, times(1)).save(any());
    }

    @DisplayName("블로그 글 전체 목록 조회")
    @Test
    void testArticleList() throws Exception {
        //given
        Long id = 123L;
        doReturn(new Article("mock_title","mock_content"))
                .when(blogService).findById(anyLong());

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", id));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("title").value("mock_title"))
                .andExpect(jsonPath("content").value("mock_content"));

    }

    @DisplayName("블로그 글 조회 400 Error Code")
    @Test
    void testGetArticleById_badRequest() throws Exception {
        doReturn(null).when(blogService).findById(anyLong());

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", 1));

        //then
        resultActions.andExpect(status().isBadRequest());
    }
}