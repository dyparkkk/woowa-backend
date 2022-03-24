package com.example.woowabackend.Post.web;

import com.example.woowabackend.Post.repository.TagRepository;
import com.example.woowabackend.Post.service.PostTagService;
import com.example.woowabackend.Post.web.dto.TagSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class TagController {


    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostTagService postTagService;

    @GetMapping("/tags/form")
    public String getTagForm(){
        return "tag/tagForm";
    }

    @PostMapping("/tags")
    public String addTag(TagSaveDto tagSaveDto) {
        tagRepository.save(tagSaveDto.toEntity());
        return "redirect:/";


    }


}
