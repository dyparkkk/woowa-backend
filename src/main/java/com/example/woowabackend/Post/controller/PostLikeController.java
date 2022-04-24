package com.example.woowabackend.Post.controller;

import com.example.woowabackend.Post.controller.dto.PostResponseDto;
import com.example.woowabackend.Post.service.PostLikeService;
import com.example.woowabackend.Post.service.PostService;
import static com.example.woowabackend.Post.controller.dto.PostResponseDto.*;

import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.domain.SessionConst;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;


@Slf4j
@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostService postService;
    private final PostLikeService postLikeService;
    private final MemberRepository memberRepository;

    @DeleteMapping("/like/{id}")
    public PostDeleteLikeResponseDto cancelLike(@PathVariable("id") Long id,
                                                @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = true) String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        Long memberId = member.getId();

        if (memberId != null) {
            postLikeService.DeleteCount(id);
        }
        return postLikeService.cancelLike(memberId, id);
    }

    @GetMapping("/like/{id}")
    public PostsAddListResponseDto addLike(@PathVariable("id") Long id,
                                           @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = true) String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        Long memberId = member.getId();

        postLikeService.count(id);

        return postLikeService.addLike(memberId,id);
    }

}
