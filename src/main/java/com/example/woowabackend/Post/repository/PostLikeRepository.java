package com.example.woowabackend.Post.repository;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<Long> countByPost(Post post);
    Optional<PostLike> findByMemberIdAndPostId(Long memberId, Long postId);
    Optional<PostLike> findByPostId(Long postId);
}
