package com.example.woowabackend.comment.controller;

import com.example.woowabackend.comment.controller.dto.CommentListResponseDto;
import com.example.woowabackend.comment.controller.dto.CommentSaveDto;
import com.example.woowabackend.comment.domain.CommentResponse;
import com.example.woowabackend.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.woowabackend.comment.controller.dto.CommentSaveDto.*;
import static com.example.woowabackend.member.controller.SessionConst.LOGIN_MEMBER;

@RestController
@Slf4j
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    // 댓글 조회
    @GetMapping("/api/comment/{postId}")
    public ResponseEntity<CommentResponse> list(
            @PathVariable Long postId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentService.commentFindAll(postId, pageable);
    }

    // 댓글 작성
    @PostMapping("/api/comment/{postId}/comment")
    public ResponseEntity commentSave(@SessionAttribute(value = LOGIN_MEMBER, required = true) String userId,
            @RequestBody CommentSaveDto commentSaveDto, @PathVariable Long postId){
        return commentService.commentCreate(userId, commentSaveDto, postId);
    }

    // 댓글, 대댓글 수정
    @PutMapping("/api/comment/{postId}/comment/modify")
    public ResponseEntity commentModify(@SessionAttribute(value = LOGIN_MEMBER, required = true) String userId,
                                      @RequestBody CommentSaveDto commentSaveDto){
        return commentService.commentUpdate(userId, commentSaveDto);
    }

    // 대댓글 작성
    @PostMapping("/api/comment/{postId}/comment/{parentId}")
    public ResponseEntity childCommentSave(@SessionAttribute(value = LOGIN_MEMBER, required = true) String userId,
            @RequestBody CommentSaveDto commentSaveDto, @PathVariable Long postId, @PathVariable Long parentId) {
        return commentService.childCommentCreate(userId, commentSaveDto, postId, parentId);
    }

    // 댓글 삭제 (디비에서 삭제 안하고 delYN으로 표기)
    @PutMapping("/api/comment/{postId}/comment")
    public ResponseEntity commentDelete(@SessionAttribute(value = LOGIN_MEMBER, required = true) String userId,
                                            @RequestBody CommentSaveDto commentSaveDto, @PathVariable Long postId){
        return commentService.commentDelete(userId, commentSaveDto, postId);
    }

}
