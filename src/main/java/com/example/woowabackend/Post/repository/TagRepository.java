package com.example.woowabackend.Post.repository;


import com.example.woowabackend.Post.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);

    //List<Tag> findByTagId(Long tagId);

}
