package com.example.woowabackend.Post.web.dto;

import com.example.woowabackend.Post.domain.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String auth;
    private int viewCut;


    public PostResponseDto(Post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.auth = entity.getAuth();
        this.viewCut = entity.getViewCnt();

    }
}
