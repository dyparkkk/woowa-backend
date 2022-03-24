package com.example.woowabackend.Post.web;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.Scrap;
import com.example.woowabackend.Post.repository.ScrapRepository;
import com.example.woowabackend.Post.service.PostService;
import com.example.woowabackend.Post.web.dto.PostResponseDto;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/post/save")
    public String postSave() {
        return "post-save";
    }

    @GetMapping("/post/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model) {
        PostResponseDto dto = postService.findById(id);
        System.out.println(id + "id");
        model.addAttribute("post", dto);
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
