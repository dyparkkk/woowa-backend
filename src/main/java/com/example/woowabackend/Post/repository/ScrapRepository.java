package com.example.woowabackend.Post.repository;

import com.example.woowabackend.Post.domain.PostLike;
import com.example.woowabackend.Post.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findAllByMemberId(Long memberId);

    Optional<Scrap> findByMemberIdAndPostId(Long memberId, Long postId);



}
