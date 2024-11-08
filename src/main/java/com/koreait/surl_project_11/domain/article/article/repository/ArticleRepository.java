package com.koreait.surl_project_11.domain.article.article.repository;


import com.koreait.surl_project_11.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByIdInOrderByTitleDescIdAsc(List<Long> ids);
    List<Article> findByTitleContaining(String title);
    List<Article> findByTitleAndBody(String title, String body);
}
