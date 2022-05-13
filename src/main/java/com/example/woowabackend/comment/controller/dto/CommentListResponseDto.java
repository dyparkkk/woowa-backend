package com.example.woowabackend.comment.controller.dto;

import com.example.woowabackend.comment.domain.Comment;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CommentListResponseDto {
    private Long id;
    private Long memberId;
    private Long parentId;
    private String content;
    private LocalDateTime createDate;
    private String deleteYN;
    private Long likeCnt;
    private int nowPage;
    private int lastPage;

    //private String deletedComment;

    public CommentListResponseDto(Comment comment,  Page<Comment> page) {
        this.id = comment.getId();
        this.memberId = comment.getMember().getId();
        this.parentId = comment.getParentId();
        this.content = comment.getContent();
        this.createDate = comment.getCreatedDate();
        this.deleteYN = comment.getDeleteYN();
        this.likeCnt = comment.getLikeCnt();
        this.nowPage = page.getNumber();
        this.lastPage = page.getTotalPages() - 1;

        //this.lastPage = pageable.get
        //this.deletedComment = "삭제된 댓글입니다.";
    }
}
