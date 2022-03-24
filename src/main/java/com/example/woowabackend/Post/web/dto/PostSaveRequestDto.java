package com.example.woowabackend.Post.web.dto;

import com.example.woowabackend.Post.domain.Post;

import com.example.woowabackend.Post.domain.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String auth;
    private String tags;



    @Builder
    public PostSaveRequestDto(String title, String content, String auth,String tags){
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
