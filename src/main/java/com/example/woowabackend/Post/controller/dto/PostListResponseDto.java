package com.example.woowabackend.Post.controller.dto;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.comment.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String auth;
    private LocalDateTime modifiedDate;
    private Long viewCnt;
    private Long commentCnt;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.auth = entity.getAuth();
        this.modifiedDate = entity.getModifiedDate();
        this.viewCnt = entity.getViewCnt();
        this.commentCnt = entity.getCommentCnt();
    }


}
