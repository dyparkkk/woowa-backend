package com.example.woowabackend.comment.controller.dto;

import com.example.woowabackend.comment.domain.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommentListResponseDto {
    private Long id;
    private Long memberId;
    private String userName;
    private Long parentId;
    private String content;
    private LocalDateTime createDate;
    private String deleteYN;
    private Long likeCnt;
    private boolean likeCheck;
    private List<ChildListResponseDto> childComment;

    public CommentListResponseDto(Comment comment, List<ChildListResponseDto> childComment) {
        List<ChildListResponseDto> child = childComment.stream()
                .filter(p -> p.getParentId().equals(comment.getId()))
                .collect(Collectors.toList());

        this.id = comment.getId();
        this.memberId = comment.getMember().getId();
        this.userName = comment.getMember().getName();
        this.parentId = comment.getParentId();
        this.content = comment.getContent();
        this.createDate = comment.getCreatedDate();
        this.deleteYN = comment.getDeleteYN();
        this.likeCnt = comment.getLikeCnt();
        this.likeCheck = comment.likeCheck(getUserName());
        this.childComment = child;

    }

}
