package com.example.woowabackend.Post.domain;

import com.example.woowabackend.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Scrap {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 연관관계 편의 메서드
    public void setMember(Member member){
        if(this.member != null)
            this.member.getScraps().remove(this);
        this.member = member;
        member.getScraps().add(this);
    }

}
