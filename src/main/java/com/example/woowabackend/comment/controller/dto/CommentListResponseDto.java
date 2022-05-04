package com.example.woowabackend.comment.controller.dto;

import com.example.woowabackend.comment.domain.Comment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class CommentListResponseDto {
    private Long id;
    private Long memberId;
    private Long parentId;
    private String content;
    private LocalDateTime createDate;
    private String deleteYN;
    //private String deletedComment;

    public CommentListResponseDto(Comment comment) {
        this.id = comment.getId();
        this.memberId = comment.getMember().getId();
        this.parentId = comment.getParentId();
        this.content = comment.getContent();
        this.createDate = comment.getCreatedDate();
        this.deleteYN = comment.getDeleteYN();
        //this.deletedComment = "삭제된 댓글입니다.";
    }
}
