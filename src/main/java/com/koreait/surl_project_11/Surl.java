package com.koreait.surl_project_11;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Surl {
    private long id;
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime modifyDate = LocalDateTime.now();
    private String body;
    private String url;
}
