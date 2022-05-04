package com.example.woowabackend.Post.repository;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostTag;
import com.example.woowabackend.Post.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    Optional<PostTag> findByPostAndTag(Post post, Tag tag);
    Optional<PostTag> findByTag(Tag tag);
    Optional<PostTag> findByPostIdAndTagId(Long postId,Long tagId);
    List<PostTag> findByPostId(Long postId);
    List<PostTag> findByTagId(Long tagId);



}
