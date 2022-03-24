package com.example.woowabackend.Post.repository;

import com.example.woowabackend.Post.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findAllByMemberId(Long memberId);



}
