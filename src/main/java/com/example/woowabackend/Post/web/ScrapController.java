package com.example.woowabackend.Post.web;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.Scrap;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.Post.repository.ScrapRepository;
import com.example.woowabackend.Post.service.PostService;
import com.example.woowabackend.Post.web.dto.PostScrapResponseDto;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import com.example.woowabackend.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ScrapController {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ScrapRepository scrapRepository;
    private final LoginService loginService;
    private final PostService postService;

    @GetMapping("/user/scrap/")
    public Object scrapList(@RequestParam Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<Scrap> scrapList = scrapRepository.findAllByMemberId(memberId);

        List<Post> boardList = new ArrayList<>();
        for (int i = 0; i < scrapList.size(); i++)
            boardList.add(scrapList.get(i).getPost());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/v1/post/scrap")
    public ResponseEntity userScrapAdd(@RequestParam Long memberId, @RequestParam Long postId ){
        Post post = postRepository.findById(postId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();
        Scrap scrap = new Scrap(post,member);
        PostScrapResponseDto dto = new PostScrapResponseDto();
        scrap.setPost(post);
        scrap.setMember(member);
        scrapRepository.save(scrap);
        dto.status = true;
        dto.data ="success";
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity userScrapRemove(@RequestParam Long memberId, @RequestParam Long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();
        Scrap scrap = new Scrap(post,member);
        PostScrapResponseDto dto = new PostScrapResponseDto();
        scrap.setPost(post);
        scrap.setMember(member);
        scrapRepository.delete(scrap);
        dto.status = true;
        dto.data ="success";
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
