package com.estsoft.blogjpa.repository;

import com.estsoft.blogjpa.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
//jpa에서 제공해주는 메소드를 호출

    @Modifying
    @Query("UPDATE Article SET title = :title WHERE id=:id ")
    void updateTitle(Long id, String title);
}
