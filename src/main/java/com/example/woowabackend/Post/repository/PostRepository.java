package com.example.woowabackend.Post.repository;


import com.example.woowabackend.Post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("Select p FROM Post p order by p.id DESC")
    List<Post> findAllDesc();

    List<Post> findAllByDeleteYn(String deleteYn);

    @Modifying
    @Transactional
    @Query("update Post p set p.viewCnt = p.viewCnt + 1 where p.id = :id")
    Integer updateView(@Param("id") Long id);

}