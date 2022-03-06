package com.example.woowabackend.comment.service;

import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.domain.CommentLike;
import com.example.woowabackend.comment.repo.CommentLikeRepository;
import com.example.woowabackend.comment.repo.CommentRepository;
import com.example.woowabackend.member.domain.Member;
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

/* Memeber merge 이 후 사용
public boolean addLike(Member member, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        //중복 좋아요 방지
        if(isNotAlreadyLike(member, comment)){
            commentLikeRepository.save(new CommentLike(member, comment));
            return true;
        }
        return false;
    }
    //사용자가 이미 좋아요 한 댓글인지 체크
    private boolean isNotAlreadyLike(Member member, Comment comment){
        return commentLikeRepository.findByMemberAndComment(member, comment).isEmpty();
    }*/

    public boolean addLike(Long commentId) {
    Comment comment = commentRepository.findById(commentId).orElseThrow();

    commentRepository.addLikes(commentId);
    log.info("Like Count Add success");

    //중복 좋아요 방지
    if(isNotAlreadyLike(comment)){
        commentLikeRepository.save(new CommentLike(comment));
        log.info("Comment Save Success");
        return true;
        }
    return false;
    }

    public boolean deleteLike(Long commentId) {
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

    //사용자가 이미 좋아요 한 댓글인지 체크
    private boolean isNotAlreadyLike(Comment comment){
        return commentLikeRepository.findByComment(comment).isEmpty();
    }
}
