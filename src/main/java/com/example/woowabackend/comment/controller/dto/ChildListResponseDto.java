package com.example.woowabackend.comment.controller.dto;

import com.example.woowabackend.comment.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChildListResponseDto {
    private Long id;
    private Long memberId;
    private String userName;
    private Long parentId;
    private String content;
    private LocalDateTime createDate;
    private String deleteYN;
    private Long likeCnt;
    private boolean likeCheck;

    public ChildListResponseDto(Comment comment) {
        this.id = comment.getId();
        this.memberId = comment.getMember().getId();
        this.userName = comment.getMember().getName();
        this.parentId = comment.getParentId();
        this.content = comment.getContent();
        this.createDate = comment.getCreatedDate();
        this.deleteYN = comment.getDeleteYN();
        this.likeCnt = comment.getLikeCnt();
        this.likeCheck = comment.likeCheck(getUserName());
    }
}
