package com.example.woowabackend.Post.controller;

import static com.example.woowabackend.Post.controller.dto.PostResponseDto.*;

import com.example.woowabackend.Post.controller.dto.PostResponseDto;
import com.example.woowabackend.Post.service.FileSystemStorageService;
import com.example.woowabackend.Post.service.PostService;
import com.example.woowabackend.Post.controller.dto.PostSaveRequestDto;
import com.example.woowabackend.Post.controller.dto.PostUpdateRequestDto;
import com.example.woowabackend.member.controller.dto.MemberDto;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import com.example.woowabackend.security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;
    private final MemberRepository memberRepository;
    private final MyUserDetailsService myUserDetailsService;

    private final FileSystemStorageService storageService;

    @PostMapping("/api/post")
    public PostCreateResponseDto save(@RequestBody PostSaveRequestDto requestDto,
                                      @RequestParam(value = "tags", defaultValue = "false") List<String> tags){

        PostResponseDto responseDto= new PostResponseDto();
        responseDto.setHashtag(tags);

       postService.save(requestDto,tags);

        return postService.save(requestDto,tags);
    }

    @PutMapping("/api/post/{id}")
    public PostUpdateResponseDto update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public PostDeleteResponseDto delete(@PathVariable Long id) {

       return  postService.delete(id);
    }

    @GetMapping("/api/post/{id}")
    public PostResponseDto findById (@PathVariable Long id){
        return postService.findById(id);
    }


    @GetMapping(value = "", params = {"tag"})
    public String getFilteredPosts(Model model, @RequestParam String tag) {
        model.addAttribute("posts", postService.findAllByTag(tag));
        return "post/index";
    }

    @GetMapping("/form")
    public String getPostForm(Model model) {
        model.addAttribute("tags", postService.findTags());
        return "post/form";
    }
}
