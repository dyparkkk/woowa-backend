package com.example.woowabackend.Post.domain;

import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String tagList; // string 줄줄이로
    private Long viewCnt;
    private Long commentCnt;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes = new ArrayList<>();

}
