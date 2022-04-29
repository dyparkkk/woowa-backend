package com.example.woowabackend.Post.controller.dto;

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


    public Tag toEntity() {
        return Tag.builder()
                .name(name)
                .build();
    }
}
