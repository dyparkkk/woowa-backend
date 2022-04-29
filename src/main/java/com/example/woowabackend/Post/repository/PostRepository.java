package com.example.woowabackend.Post.repository;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("Select p FROM Post p order by p.id DESC")
    List<Post> findAllDesc();

    @Query("SELECT tag FROM Tag tag ORDER BY tag.name")
    @Transactional(readOnly = true)
    List<Tag> findTags();

    @Query("SELECT tag FROM Tag tag WHERE tag.name = ?1")
    Tag findTagByName(String name);
}