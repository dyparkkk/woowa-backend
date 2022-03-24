package com.example.woowabackend.comment.domain;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.util.domain.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> likes;

    @ColumnDefault("0")
    private Long likeCnt;

    private String deleteYN = "N";

    public void update(){
        this.deleteYN = "Y";
    }


 /*   public void confirmMember(Member member){
        this.member = member;
        member.addComment(this);
    }

    public void confirmPost(Post post){
        this.post = post;
        post.addComment(this);
    }

    public void confirmParent(Comment parent){
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Comment child){
        children.add(child);
    }

    public void updateContent(String content){
        this.content = content;
    }*/

/*   public Comment(String content, Member member, Post post, Comment parent){
        this.content = content;
        this.member = member;
        this.post = post;
        this.parent = parent;
        this.isRemoved = false;
    }*/

}