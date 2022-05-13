package com.example.woowabackend.comment.repository;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //List<Comment> findByPostId(Long postId, Pageable pageable);
    Page<Comment> findByPostId(Long postId, Pageable pageable);
}
