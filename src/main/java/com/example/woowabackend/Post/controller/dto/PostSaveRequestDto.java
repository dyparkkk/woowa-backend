package com.example.woowabackend.Post.controller.dto;

import com.example.woowabackend.Post.domain.Post;

import com.example.woowabackend.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String auth;
    private String tags;
    private Long member;
    private Long postId;



    @Builder
    public PostSaveRequestDto(String title, String content, String auth, String tags){
        this.title = title;
        this.content = content;
        this.auth = auth;
        this.tags = tags;

    }


    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .auth(auth)
                .build();
    }



}
