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
public class School {

    @Id
    @GeneratedValue
    private String id;

    private String name;
    private Long schoolTypeId; // mapping
    private Long schoolLocationId; // mapping

}
