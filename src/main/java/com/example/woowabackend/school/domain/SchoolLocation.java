package com.example.woowabackend.school.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SchoolLocation{

    @Id
    private String id;

    private String addr; // 시, 구 등등으로 나눌 수도 있음
}
