package com.example.woowabackend.comment.domain;

import com.example.woowabackend.member.domain.Member;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentLike {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

/*  Member merge 이후 사용
public CommentLike(Member member, Comment comment){
        this.member = member;
        this.comment = comment;
    }*/
    public CommentLike(Comment comment){
    this.comment = comment;
    }

}
