package com.example.woowabackend.comment.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private Long postId; // mapping
    private Long memberId; // mapping

    private String content;
    private Integer arg; // 댓글인지 대댓글인지
    private Integer order;
    private Integer groupNum;
    private Long likeCnt;


}
