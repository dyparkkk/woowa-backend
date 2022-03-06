package com.example.woowabackend.Post.repo;

import com.example.woowabackend.Post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
