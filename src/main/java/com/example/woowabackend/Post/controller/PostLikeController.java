package com.example.woowabackend.Post.controller;

import com.example.woowabackend.Post.controller.dto.PostListResponseDto;
import com.example.woowabackend.Post.repository.PostLikeRepository;
import com.example.woowabackend.Post.service.PostLikeService;
import static com.example.woowabackend.Post.controller.dto.PostResponseDto.*;
import static com.example.woowabackend.member.controller.SessionConst.*;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;


@Slf4j
@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;
    private final MemberRepository memberRepository;
    private final PostLikeRepository postLikeRepository;

    @DeleteMapping("/like/{id}")
    public PostDeleteLikeResponseDto cancelLike(@PathVariable("id") Long id,
                                                @SessionAttribute(value = LOGIN_MEMBER, required = true) String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        Long memberId = member.getId();

        if (memberId != null) {
            postLikeService.DeleteCount(id);
        }
        return postLikeService.cancelLike(memberId, id);
    }

    @PostMapping("/like/{id}")
    public PostsAddListResponseDto addLike(@PathVariable("id") Long id,
                                       @SessionAttribute(value = LOGIN_MEMBER, required = true) String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        Long memberId = member.getId();

        return postLikeService.addLike(memberId,id);
    }

}
