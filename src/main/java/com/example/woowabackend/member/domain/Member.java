package com.example.woowabackend.member.domain;

import com.example.woowabackend.Post.domain.PostLike;
import com.example.woowabackend.Post.domain.Scrap;
import com.example.woowabackend.comment.domain.CommentLike;
import com.example.woowabackend.school.domain.School;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String pw;
    private String name;
    private String phoneNumber;
    private String birth;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "school_id")
    private School schoolCode;

    private Boolean schoolCheck;
    private Boolean alarmCheck;

    @OneToMany(mappedBy = "member")
    private List<Scrap> scraps = new ArrayList<>(); // 중복을 방지하기 위해서 Set 사용 고려

    @OneToMany(mappedBy = "member")
    private List<PostLike> postLikes = new ArrayList<>(); // 중복을 방지하기 위해서 Set 사용 고려

    @OneToMany(mappedBy = "member")
    private List<CommentLike> commentLike = new ArrayList<>(); // 중복을 방지하기 위해서 Set 사용 고려

    // comment 양방향 필요 ?
}
