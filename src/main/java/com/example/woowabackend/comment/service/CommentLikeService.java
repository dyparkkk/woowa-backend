package com.example.woowabackend.comment.service;

import com.example.woowabackend.comment.controller.dto.CommentSaveDto.*;
import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.domain.CommentLike;
import com.example.woowabackend.comment.repository.CommentLikeRepository;
import com.example.woowabackend.comment.repository.CommentRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity addLike(String username, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        Member member = memberRepository.findByUserId(username).orElseThrow();

        //중복 좋아요 방지
        if(isNotAlreadyLike(member, comment)){
            commentLikeRepository.save(new CommentLike(member, comment));
            comment.increaseLikeCnt();
            return new ResponseEntity(HttpStatus.OK.value(), HttpStatus.OK);
        }
        return new ResponseEntity("Duplicated Like", HttpStatus.BAD_REQUEST);
    }
    //사용자가 이미 좋아요 한 댓글인지 체크
    private boolean isNotAlreadyLike(Member member, Comment comment){
        return commentLikeRepository.findByMemberAndComment(member, comment).isEmpty();
    }

    public ResponseEntity deleteLike(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("commentId Not Found"));
        CommentLike commentLike = commentLikeRepository.findByCommentId(commentId).orElseThrow(() -> new IllegalArgumentException("commentId Not Found for commentLike"));
        commentLikeRepository.delete(commentLike);

        comment.decreaseLikeCnt();

        if(comment.getLikeCnt() < 0){
            comment.getLikeCnt();
        }

        return new ResponseEntity(HttpStatus.OK.value(), HttpStatus.OK);
    }
}
