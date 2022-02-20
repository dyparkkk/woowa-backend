package com.example.woowabackend.school.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class School {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_type_id")
    private SchoolType schoolType; // 이거 필요한가 ? -> 일단 만들기는 함...

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_location_id")
    private SchoolLocation SchoolLocation; // 이거는 enum 타입이 더 좋지않을까? -> 일단 만들기는 함

}
