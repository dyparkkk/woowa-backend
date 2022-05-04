package com.example.woowabackend.comment.controller;

import com.example.woowabackend.comment.controller.dto.CommentSaveDto;
import com.example.woowabackend.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.woowabackend.comment.controller.dto.CommentSaveDto.*;

@RestController
@Slf4j
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    // 댓글 작성
    @PostMapping("/api/post/{postId}/comment")
    public SuccessResponseDto commentSave(@RequestBody CommentSaveDto commentSaveDto, @PathVariable Long postId){
        return commentService.create(commentSaveDto, postId);
    }

    // 대댓글 작성
    @PostMapping("/api/post/{postId}/comment/{parentId}")
    public SuccessResponseDto childCommentSave(@RequestBody CommentSaveDto commentSaveDto, @PathVariable Long postId, @PathVariable Long parentId) {
        return commentService.childCreate(commentSaveDto, postId, parentId);
    }

    // 댓글 삭제 (디비에서 삭제 안하고 delYN으로 표기)
    @PutMapping("/api/post/{postId}/comment")
    public SuccessResponseDto commentDelete(@RequestBody CommentSaveDto commentSaveDto){
        return commentService.delete(commentSaveDto.getCommentId());
    }

}
