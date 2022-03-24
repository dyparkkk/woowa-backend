package com.example.woowabackend.comment.repository;

import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.domain.CommentLike;
import com.example.woowabackend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    // Member merge 이후 사용
    Optional<CommentLike> findByMemberAndComment(Member member, Comment comment);

    @Modifying
    @Query("DELETE FROM CommentLike WHERE comment_id = :commentId")
    void deleteLikes(long commentId);
}
