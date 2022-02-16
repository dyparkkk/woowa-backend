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
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String auth;
    private String img;
    private Long likeCnt;
    private Long memberId;  // mapping 필요
    private String tagList; // string 줄줄이로
    private Long boardId;
    private Long viewCnt;
    private Long commentCnt;


}
