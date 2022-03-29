package com.example.woowabackend.Post.controller.dto;

import com.example.woowabackend.Post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String auth;

    List<String> tags;
    public boolean status;
    public String data;


    public PostResponseDto(Post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.auth = entity.getAuth();
    }

    public PostResponseDto() {
        this.title = getTitle();
        this.content = getContent();
        this.auth = getAuth();
    }

    @Data
    @AllArgsConstructor
    public static class PostCreateResponseDto {
        private final Boolean success = true;

    }

    @Data
    @AllArgsConstructor
    public static class PostUpdateResponseDto {
       private final Boolean success = true;

    }

    @Data
    @AllArgsConstructor
    public static class PostDeleteResponseDto {
        private final Boolean success = true;

    }

    @Data
    @AllArgsConstructor
    public static class PostLikeDeleteResponseDto {
        private final Boolean success = true;

    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public void setHashtag(List<String>tags){
        this.tags = tags;
    }
    public void setAuth(String auth){this.auth = auth;}
}
