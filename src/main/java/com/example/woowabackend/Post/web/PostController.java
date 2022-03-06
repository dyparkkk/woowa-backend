package com.example.woowabackend.Post.web;

import com.example.woowabackend.Post.service.PostService;
import com.example.woowabackend.Post.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/save")
    public String postSave(){
        return "post-save";
    }

    @GetMapping("/post/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostResponseDto dto = postService.findById(id);
        System.out.println(id + "id");
        model.addAttribute("post",dto);
        return "post-update";
    }

    @GetMapping("/post/detail/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model) {
        PostResponseDto dto = postService.findById(id);
        postService.updateView(id); // views ++
        model.addAttribute("post", dto);
        return "post/detail";
    }

}
