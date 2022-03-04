package com.example.woowabackend.Post.web.dto;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String auth;



    @Builder
    public PostSaveRequestDto(String title, String content, String auth){
        this.title = title;
        this.content = content;
        this.auth = auth;

    }


    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .auth(auth)
                .build();
    }

}
