package com.example.woowabackend.Post.domain;

import com.example.woowabackend.Post.repository.PostLikeRepository;
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
    private String deleteYn;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

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
        this.auth = member.getName();
        this.viewCnt =  0L;
        this.likeCnt = 0L;
        this.commentCnt = 0L;
        this.deleteYn = "N";
        this.member = member;

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

    public boolean likeCheck(String userId){
        for(PostLike postLike : postLikes) {
            if (postLike.getMember().getId().equals(userId));
                return true;
        }
        return false;
    }

}
