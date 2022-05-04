package com.example.woowabackend.Post.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostLike;
import com.example.woowabackend.Post.repository.PostLikeRepository;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.example.woowabackend.Post.controller.dto.PostResponseDto.*;
import static com.example.woowabackend.member.controller.SessionConst.LOGIN_MEMBER;

@RequiredArgsConstructor
@Service
public class PostLikeService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;

    //좋아요
    public PostsAddListResponseDto addLike(Long memberId, Long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();


        //중복방지
        if(isNotAlreadyLike(memberId,postId)){
            count(postId);
            postLikeRepository.save(new PostLike(post,member));
        }
        else{ throw new RuntimeException("이미 좋아요를 눌렀습니다");}

        return new PostsAddListResponseDto();
    }

    //좋아요 취소
    public PostDeleteLikeResponseDto cancelLike(Long memberId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();
        PostLike postlike = postLikeRepository.findByMemberIdAndPostId(member.getId(),post.getId()).orElseThrow();
        postLikeRepository.delete(postlike);
        return new PostDeleteLikeResponseDto();
    }

    public void checkLike(Long memberId, Long postId){

        Post post = postRepository.findById(postId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();
        if(isNotAlreadyLike(memberId,postId)){

            postLikeRepository.save(new PostLike(post,member));
        }


    }

    //좋아요 수 카운트
    public void count(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new  IllegalArgumentException("실패"));
        post.increaseLikeCnt();
        postRepository.save(post);
    }

    //좋아요 취소 카운트
    public void DeleteCount(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new  IllegalArgumentException("실패"));
        post.deleteLikeCnt();
        postRepository.save(post);
    }


    //이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(Long memberId, Long postId){
        return postLikeRepository.findByMemberIdAndPostId(memberId,postId).isEmpty();
    }

}
