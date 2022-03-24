package com.example.woowabackend.comment.controller;

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
    public Long addLike(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long commentId){
        boolean result;
        String username = userDetails.getUsername();
        //String username = "test2";
        result = commentLikeService.addLike(username,commentId);
        return 200L;
    }

    @DeleteMapping("/api/comment/{commentId}/deleteLike")
    public Long deleteLike(@PathVariable Long commentId){
        Member member = Member.builder().build();
        boolean result;
        result = commentLikeService.deleteLike(member,commentId);
        return 200L;
    }
}
