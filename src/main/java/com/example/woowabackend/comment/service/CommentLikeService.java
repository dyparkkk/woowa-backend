package com.example.woowabackend.comment.service;

import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.domain.CommentLike;
import com.example.woowabackend.comment.repository.CommentLikeRepository;
import com.example.woowabackend.comment.repository.CommentRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

public boolean addLike(String username, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        Member member = memberRepository.findByUserId(username).orElseThrow();

        Long memberId = member.getId();

        //중복 좋아요 방지
        if(isNotAlreadyLike(member, comment)){
            log.info("isNotAlreadyLike = True");
            commentLikeRepository.save(new CommentLike(member, comment));
            commentRepository.addLikes(comment.getId());
            return true;
        }
        return false;
    }
    //사용자가 이미 좋아요 한 댓글인지 체크
    private boolean isNotAlreadyLike(Member member, Comment comment){
        log.info("isNotAlreadyLike = False");
        return commentLikeRepository.findByMemberAndComment(member, comment).isEmpty();
    }

    public boolean deleteLike(Member member, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        commentLikeRepository.deleteLikes(commentId);
        log.info("Like Delete Success");

        commentRepository.subLikes(commentId);
        log.info("Like Count Subtract Success");

        if(comment.getLikeCnt() < 0){
            commentRepository.defaultLikes(commentId);
        }

        return true;
    }
}
