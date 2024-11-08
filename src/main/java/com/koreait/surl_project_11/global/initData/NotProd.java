package com.koreait.surl_project_11.global.initData;

import com.koreait.surl_project_11.domain.article.article.entity.Article;
import com.koreait.surl_project_11.domain.article.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final ArticleRepository articleRepository;

    @Bean
    public ApplicationRunner initNotProd(){
        return args -> {
            System.out.println("Not Prod.initNotProd1");
            System.out.println("Not Prod.initNotProd2");
            System.out.println("Not Prod.initNotProd3");

            articleRepository.save(
                    Article.builder()
                            .title("제목")
                            .body("내용")
                            .build()
            );
        };
    }
}
