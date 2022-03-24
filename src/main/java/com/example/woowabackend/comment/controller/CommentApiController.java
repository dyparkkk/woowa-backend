package com.example.woowabackend.comment.controller;

import com.example.woowabackend.comment.dto.CommentSaveDto;
import com.example.woowabackend.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    // 부모 댓글 작성
    @PostMapping("/api/post/{postId}/comment")
    public Long commentSave(@RequestBody CommentSaveDto commentSaveDto) {
        commentService.create(commentSaveDto);
        return 200L;
    }

    // 자식 댓글 작성
    @PostMapping("/api/post/{postId}/comment/{parentId}")
    public Long childCommentSave(@RequestBody CommentSaveDto commentSaveDto) {
        commentService.childCreate(commentSaveDto);
        return 200L;
    }

    // 댓글 삭제 (디비에서 삭제 안하고 delYN으로 표기)
    @PutMapping("/api/post/{postId}/comment")
    public Long commentDelete(@RequestBody CommentSaveDto commentSaveDto){
        commentService.delete(commentSaveDto.getCommentId());
        return commentSaveDto.getCommentId();
    }
}
