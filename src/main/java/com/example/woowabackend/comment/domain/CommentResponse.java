package com.example.woowabackend.comment.domain;

import com.example.woowabackend.comment.controller.dto.CommentListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Integer code;
    private HttpStatus httpStatus;
    private List<CommentListResponseDto> result;
    private int nowPage;
    private int lastPage;
}
