package com.example.woowabackend.Post.controller;

import com.example.woowabackend.Post.controller.dto.PostResponseDto;
import com.example.woowabackend.Post.service.PostLikeService;
import com.example.woowabackend.Post.service.PostService;
import static com.example.woowabackend.Post.controller.dto.PostResponseDto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostService postService;
    private final PostLikeService postLikeService;

    @DeleteMapping("/like/{id}")
    public void cancelLike(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
        if (memberId != null) {
            postLikeService.cancelLike(memberId, id);
            postLikeService.DeleteCount(id);
        }
    }

    @GetMapping("/like/{id}")
    public void addLike(@PathVariable("id") Long id,  @RequestParam("memberId")Long memberId) {
        postLikeService.addLike(memberId,id);
        postLikeService.count(id);
    }

}
