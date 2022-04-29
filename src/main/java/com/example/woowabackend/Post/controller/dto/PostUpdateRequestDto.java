package com.example.woowabackend.Post.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {

    private String title;
    private String content;
    private String tags;

    @Builder
    public PostUpdateRequestDto(String title, String content, String tags){
        this.title = title;
        this.content = content;
        this.tags = tags;
    }
}
