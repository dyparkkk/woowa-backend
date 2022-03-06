package com.example.woowabackend.Post.repository;


import com.example.woowabackend.Post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("Select p FROM Post p order by p.id DESC")
    List<Post> findAllDesc();



}