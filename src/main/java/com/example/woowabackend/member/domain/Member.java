package com.example.woowabackend.member.domain;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostLike;
import com.example.woowabackend.Post.domain.Scrap;
import com.example.woowabackend.comment.domain.CommentLike;
import com.example.woowabackend.school.domain.School;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String pw;

    @Column(name = "user_name")
    private String name;


    private PhoneNumber phoneNumber;
    private Birth birth;
    private String roles;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "school_id")
//    private School schoolCode;

    @OneToMany(mappedBy = "member")
    private List<Post> post = new ArrayList<Post>();

    @OneToMany(mappedBy = "member")
    private List<Scrap> scraps = new ArrayList<>(); // 중복을 방지하기 위해서 Set 사용 고려

//    @OneToMany(mappedBy = "member")
//    private List<PostLike> postLikes = new ArrayList<>(); // 중복을 방지하기 위해서 Set 사용 고려
//
//    @OneToMany(mappedBy = "member")
//    private List<CommentLike> commentLike = new ArrayList<>(); // 중복을 방지하기 위해서 Set 사용 고려


    @Builder
    public Member(String userId, String pw, String name, PhoneNumber phoneNumber,Birth birth) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.roles = "ROLE_USER";
    }

    public List<String> getRoleList() {
        if(roles.length() > 0) {
            return Arrays.asList(roles.split(","));
        }
        return new ArrayList<>();
    }

}
