package com.example.woowabackend.Post.domain;

import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.util.domain.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String auth;
    private String img;
    private String deleteYn;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column
    @NotNull
    private Long viewCnt;

    @Column
    @NotNull
    private Long likeCnt;

    @NotNull
    private Long commentCnt;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Scrap> scraps = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostTag> postTags = new ArrayList<>();

    @Builder
    public Post(String title, String content, String auth, String img, Long likeCnt, Long viewCnt, Long commentCnt,String deleteYn,Member member){
        this.title = title;
        this.content = content;
        this.auth = auth;
        imgUpload(img);
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.deleteYn = deleteYn;
        this.member = member;

    }

    public Post imgUpload(String img){
        this.img = img;
        return this;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void increaseViewCnt(){
        this.viewCnt++;
    }

    public void increaseLikeCnt(){
        this.likeCnt++;
   }
    public void deleteLikeCnt(){
        this.likeCnt--;
    }

    public void delete(){
        this.deleteYn = "Y";
    }

    public boolean Tag(String tagName){
        for(PostTag postTag : postTags){
            if(postTag.getTag().getName().equals(tagName));
            return true;
        }
        return false;
    }
}
