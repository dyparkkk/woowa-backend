package com.example.woowabackend.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String memberId;
    private String pw;
    private String name;
    private String phoneNumber;
    private String birth;
    private String schoolCode;
    private Boolean schoolCheck;
    private Boolean alarmCheck;

    // postLike 추가해라 ...

}
