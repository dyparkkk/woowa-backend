package com.example.woowabackend.Post.controller.dto;

import com.example.woowabackend.Post.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String auth;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.auth = entity.getAuth();
        this.modifiedDate = entity.getModifiedDate();
    }
}
