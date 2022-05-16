package com.example.woowabackend.Post.controller.dto;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.comment.domain.Comment;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String auth;
    private String content;
    private LocalDateTime modifiedDate;
    private Long likeCnt;
    private Long viewCnt;
    private Long commentCnt;
    private boolean likeCheck;


    public PostListResponseDto(Post entity, Page<Post> page) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.auth = entity.getAuth();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
        this.likeCnt = entity.getLikeCnt();
        this.viewCnt = entity.getViewCnt();
        this.commentCnt = entity.getCommentCnt();
        this.likeCheck = entity.likeCheck(getAuth());
    }

}
