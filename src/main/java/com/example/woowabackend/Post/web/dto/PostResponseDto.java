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
    private Long viewCut;
    private Long likeCnt;
    List<String> tags;


    public PostResponseDto(Post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.auth = entity.getAuth();
        this.viewCut = entity.getViewCnt();
        this.likeCnt  = entity.getLikeCnt();

    }

    public PostResponseDto() {
        this.title = getTitle();
        this.content = getContent();
        this.auth = getAuth();
    }

//    public PostResponseDto(String entity){
//        this.title = getTitle();
//        this.content = getContent();
//        this.auth = getAuth();
//    }


    public void setHashtag(List<String>tags){
        this.tags = tags;
    }
}
