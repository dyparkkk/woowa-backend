package com.example.woowabackend.Post.web;

import com.example.woowabackend.Post.service.FileSystemStorageService;
import com.example.woowabackend.Post.service.PostService;
import com.example.woowabackend.Post.web.dto.PostResponseDto;
import com.example.woowabackend.Post.web.dto.PostSaveRequestDto;
import com.example.woowabackend.Post.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    private final FileSystemStorageService storageService;

    @PostMapping("/api/post")
    public Long save( @RequestBody PostSaveRequestDto requestDto){

        return postService.save(requestDto);
    }

    @PutMapping("/api/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public Long delete(@PathVariable Long id) {
       postService.delete(id);
       return id;
    }

    @GetMapping("/api/post/{id}")
    public PostResponseDto findById (@PathVariable Long id){
        return postService.findById(id);
    }


}
