package com.example.woowabackend.Post.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.Scrap;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.Post.repository.ScrapRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.example.woowabackend.Post.controller.dto.PostScrapResponseDto.*;

@RequiredArgsConstructor
@Service
public class ScrapService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ScrapRepository scrapRepository;

    public ScrapAddResponseDto userScrapAdd(Long postId, Long memberId){
        Post post = postRepository.findById(postId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();

        if(isNotAlreadyScrap(postId,memberId)) {
            scrapRepository.save(new Scrap(post,member));
        }

        return new ScrapAddResponseDto();
    }

    public ScrapRemoveResponseDto userScrapRemove(Long postId, Long memberId){
        Post post = postRepository.findById(postId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();
        Scrap scrap = scrapRepository.findByMemberIdAndPostId(member.getId(),post.getId()).orElseThrow();

        scrapRepository.delete(scrap);
        return new ScrapRemoveResponseDto();
    }

    //이미 스크랩한 게시물인지 체크
    private boolean isNotAlreadyScrap(Long postId,Long memberId ){
        return scrapRepository.findByMemberIdAndPostId(memberId,postId).isEmpty();
    }
}
