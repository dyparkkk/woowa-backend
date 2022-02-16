package com.example.woowabackend.Post.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {

    @Id
    @GeneratedValue
    private String id;

    private Long SchoolCodeId; // mapping
    private Integer type; // 학교게시판인지, 지역게시판인지
}
