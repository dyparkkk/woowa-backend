package com.example.woowabackend.Post.web.dto;

import com.example.woowabackend.Post.domain.Post;
import lombok.Getter;


@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String auth;
    private Long viewCut;
    private Long likeCnt;


    public PostResponseDto(Post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.auth = entity.getAuth();
        this.viewCut = entity.getViewCnt();
        this.likeCnt  = entity.getLikeCnt();

    }
}
