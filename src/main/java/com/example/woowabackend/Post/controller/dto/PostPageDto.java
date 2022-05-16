package com.example.woowabackend.Post.controller.dto;

import com.example.woowabackend.Post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPageDto {

    //조회한 페이지에 해당하는 데이터 리스트
    private List<PostListResponseDto> elements;
    //총 데이터 수
    private Long totalElements;
    //현재 페이지 번호
    private int currentPage;
    //총 페이지 수
    private int totalPages;

    public static PostPageDto of (Page<Post> postPage){
        return PostPageDto
                .builder()
                .elements(postPage.getContent().stream().map(n -> new PostListResponseDto(n,postPage)).collect(Collectors.toList()))
                .totalElements(postPage.getTotalElements())
                .totalPages(postPage.getTotalPages() -1)
                .currentPage(postPage.getNumber())
                .build();

    }
}
