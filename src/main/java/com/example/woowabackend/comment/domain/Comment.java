package com.example.woowabackend.comment.domain;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostLike;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.util.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, length = 200)
    private String content;

    @Column
    private Long parentId;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> likes;

    @ColumnDefault("0")
    private Long likeCnt;

    private String deleteYN = "N";

    public void update(){
        this.deleteYN = "Y";
    }

    public void increaseLikeCnt() {
        this.likeCnt++;
    }

    public void decreaseLikeCnt() {
        this.likeCnt--;
    }

    public boolean likeCheck(String userId){
        for(CommentLike commentLike : likes) {
            if (commentLike.getMember().getId().equals(userId));
            return true;
        }
        return false;
    }

    @Builder
    public Comment(String content, Member member, Post post, Long parentId){
        this.content = content;
        this.member = member;
        this.post = post;
        this.parentId = parentId;
        this.likeCnt = 0L;
        this.deleteYN = "N";
    }

}