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
public class Scrap {

    @Id @GeneratedValue
    private Long id;
    // memberId, postId를 pk로 설정해야함... 화이팅
}
