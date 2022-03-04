package com.example.woowabackend.Post.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostLike;
import com.example.woowabackend.Post.repository.PostLikeRepository;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostLikeService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    //좋아요
    public boolean addLike(Member member, Long postId){
        Post post = postRepository.findById(postId).orElseThrow();

        //중복방지
        if(isNotAlreadyLike(member,post)){
            postLikeRepository.save(new PostLike(post,member));
            return true;
        }
        return false;
    }

    public void cancelLike(Member member, Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        PostLike postlike = postLikeRepository.findByMemberAndPost(member, post).orElseThrow();
        postLikeRepository.delete(postlike);
    }

    /*
        1 좋아요를 count할 대상 post를 가져온다.
        2 가져온 post로 postlike테이블에 쿼리한 결과를 List에 담는다.
        3 현재 로그인한 사용자가 이미 해당 게시물에 좋아요를 눌렀는지 검사하고 결과를 List에 담아 반환한다.
     */
    public List<String> count(Long id, Member userId) {
        Post post = postRepository.findById(id).orElseThrow();

        Long likeCnt = postLikeRepository.countByPost(post).orElse(Long.valueOf("0"));

        List<String> resultData =
                new ArrayList<>(Arrays.asList(String.valueOf(likeCnt)));

        if (Objects.nonNull(userId)) {
            resultData.add(String.valueOf(isNotAlreadyLike(userId, post)));
            return resultData;
        }

        return resultData;
    }

    //이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(Member member, Post post){
        return postLikeRepository.findByMemberAndPost(member,post).isEmpty();
    }

}
