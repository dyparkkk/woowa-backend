package com.example.woowabackend.Post.controller.dto;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostTag;
import com.example.woowabackend.comment.controller.dto.CommentListResponseDto;
import com.example.woowabackend.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;


@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String auth;

    List<String> tags;

    public PostResponseDto(Post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.auth = entity.getAuth();
    }

    public PostResponseDto() {
        this.id = getId();
        this.title = getTitle();
        this.content = getContent();
        this.auth = getAuth();
        this.tags = getTags();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostTagResponseDto{
        List<String> tags = getTags();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentResponseDto{
        List<Comment> comments = getComments();
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
    public static class PostsAddListResponseDto{
        private final Boolean success = true;


    }

    @Data
    @AllArgsConstructor
    public static class PostDeleteLikeResponseDto {
        private final Boolean success = true;

    }

    public void setHashtag(List<String>tags){
        this.tags = tags;
    }

    public void setAuth(String auth){this.auth = auth;}
}
