package com.example.woowabackend.Post.controller.dto;

import com.example.woowabackend.Post.domain.Post;

import com.example.woowabackend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.lang.model.element.Name;


@Getter @Setter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String auth;
    private String name;
    private String tags;
    private Long postId;
    private Member member;


    @Builder
    public PostSaveRequestDto(String title, String content, String auth,String name, String tags){
        this.title = title;
        this.content = content;
        this.auth = auth;
        this.name = name;
        this.tags = tags;

    }


    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .auth(auth)
                .member(member)
                .build();
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
