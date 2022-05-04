package com.example.woowabackend.comment.controller.dto;

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
    private Boolean check;

    @Data
    @NoArgsConstructor
    public static class CommentResponseDto {
        private final Boolean success = true;
    }

    public static class SuccessResponseDto {
        private final Boolean success = true;
    }
}
