package com.example.woowabackend.comment.repo;

import com.example.woowabackend.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

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
