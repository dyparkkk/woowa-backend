package com.example.woowabackend.Post.web.dto;

import com.example.woowabackend.Post.domain.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagSaveDto {

    private String name;

    @Builder
    public TagSaveDto(String name){
        this.name = name;
    }

    public Tag toEntity() {
        return Tag.builder()
                .name(name)
                .build();
    }
}
