package com.example.woowabackend.comment.repo;

import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    // Member merge 이후 사용
    //Optional<CommentLike> findByMemberAndComment(Member member, Comment comment);
    Optional<CommentLike> findByComment(Comment comment);

    @Modifying
    @Query("DELETE FROM CommentLike WHERE comment_id = :commentId")
    void deleteLikes(long commentId);
}
