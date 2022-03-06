package com.example.woowabackend.comment.controller;

import com.example.woowabackend.comment.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Long addLike(@PathVariable Long commentId){
        boolean result;
        //result = commentLikeService.addLike(Member,commentId);
        result = commentLikeService.addLike(commentId);
        return 200L;
    }

    @DeleteMapping("/api/comment/{commentId}/deleteLike")
    public Long deleteLike(@PathVariable Long commentId){
        boolean result;
        //result = commentLikeService.addLike(Member,commentId);
        result = commentLikeService.deleteLike(commentId);
        return 200L;
    }
}
