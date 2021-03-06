package com.example.woowabackend.comment.controller;

import com.example.woowabackend.comment.controller.dto.CommentSaveDto.*;
import com.example.woowabackend.comment.service.CommentLikeService;
import com.example.woowabackend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.woowabackend.member.controller.SessionConst.LOGIN_MEMBER;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentLikeApiController {
    private final CommentLikeService commentLikeService;

    // 좋아요
    @PostMapping("/api/comment/{commentId}/addLike")
    public ResponseEntity addLike(@SessionAttribute(value = LOGIN_MEMBER, required = true) String userId,
                                  @PathVariable Long commentId){
        return commentLikeService.addLike(userId, commentId);
    }

    // 좋아요 취소
    @DeleteMapping("/api/comment/{commentId}/deleteLike")
    public ResponseEntity deleteLike(@PathVariable Long commentId){
        return commentLikeService.deleteLike(commentId);
    }
}
