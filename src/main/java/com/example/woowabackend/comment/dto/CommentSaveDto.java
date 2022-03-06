package com.example.woowabackend.comment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentSaveDto {
    private Long memberId;
    private Long postId;
    private Long parentId;
    private Long commentId;
    private String content;
}
