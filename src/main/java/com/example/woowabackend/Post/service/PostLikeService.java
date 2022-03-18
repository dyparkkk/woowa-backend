package com.example.woowabackend.Post.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostLike;
import com.example.woowabackend.Post.repository.PostLikeRepository;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;

    //좋아요
    public boolean addLike(Long memberId, Long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();

        //중복방지
        if(isNotAlreadyLike(memberId,postId)){
            postLikeRepository.save(new PostLike(post,member));
            return true;
        }
        return false;
    }

    public void cancelLike(Long memberId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();
        PostLike postlike = postLikeRepository.findByMemberIdAndPostId(memberId,postId).orElseThrow();
       postLikeRepository.delete(postlike);
    }

    public void count(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new  IllegalArgumentException("실패"));
        post.increaseLikeCnt();
        postRepository.save(post);

    }
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
