package com.example.woowabackend.Post.domain;

import com.example.woowabackend.school.domain.School;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {

    @Id
    @GeneratedValue
    private String id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_code")
    private School school; // 개선 고려 -> type에 따라 꼭있어야 하거나 없어도 되거나

    private Integer type; // 학교게시판인지, 지역게시판인지
}
