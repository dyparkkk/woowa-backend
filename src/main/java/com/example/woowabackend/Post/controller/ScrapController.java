package com.example.woowabackend.Post.controller;

import com.example.woowabackend.Post.controller.dto.PostScrapResponseDto;
import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.Scrap;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.Post.repository.ScrapRepository;
import static com.example.woowabackend.Post.controller.dto.PostScrapResponseDto.*;

import com.example.woowabackend.Post.service.ScrapService;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.domain.SessionConst;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ScrapController {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ScrapRepository scrapRepository;
    private final ScrapService scrapService;

    @GetMapping("/user/scrap/")
    public Object scrapList(@RequestParam Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<Scrap> scrapList = scrapRepository.findAllByMemberId(memberId);

        List<Post> boardList = new ArrayList<>();
        for (int i = 0; i < scrapList.size(); i++)
            boardList.add(scrapList.get(i).getPost());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/post/scrap/")
    public ScrapAddResponseDto userScrapAdd(@RequestParam Long postId,
                                            @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = true) String userId){
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        Long memberId = member.getId();

        Post post = postRepository.findById(postId).orElseThrow();

        return scrapService.userScrapAdd(postId,memberId);

    }

    @DeleteMapping("/api/post/scrap/")
    public ScrapRemoveResponseDto userScrapRemove(@RequestParam Long postId,
                                                  @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = true) String userId){
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        Long memberId = member.getId();
        Post post = postRepository.findById(postId).orElseThrow();

       
        return  scrapService.userScrapRemove(postId,memberId);
    }
}
 