package com.example.woowabackend.comment.repository;

import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    @Transactional
    @Modifying
    @Query("update Comment c set c.likeCnt = c.likeCnt + 1 where c.id = :id")
    Integer addLikes(Long id);

    @Transactional
    @Modifying
    @Query("update Comment c set c.likeCnt = c.likeCnt - 1 where c.id = :id")
    Integer subLikes(Long id);

    @Transactional
    @Modifying
    @Query("update Comment c set c.likeCnt = 0 where c.id = :id")
    Integer defaultLikes(Long id);
}
