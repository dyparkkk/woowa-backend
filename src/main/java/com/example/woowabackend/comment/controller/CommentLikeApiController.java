package com.example.woowabackend.comment.controller;

import com.example.woowabackend.comment.controller.dto.CommentSaveDto.*;
import com.example.woowabackend.comment.service.CommentLikeService;
import com.example.woowabackend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentLikeApiController {
    private final CommentLikeService commentLikeService;
    
    @PostMapping("/api/comment/{commentId}/addLike")
    public SuccessResponseDto addLike(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long commentId){
        //String username = userDetails.getUsername();
        String username = "test0429@naver.com";
        return commentLikeService.addLike(username,commentId);
    }

    @DeleteMapping("/api/comment/{commentId}/deleteLike")
    public SuccessResponseDto deleteLike(@PathVariable Long commentId){
        Member member = Member.builder().build();
        return commentLikeService.deleteLike(member,commentId);
    }
}
