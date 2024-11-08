package com.koreait.surl_project_11.domain.article.article.repository;


import com.koreait.surl_project_11.domain.article.article.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
