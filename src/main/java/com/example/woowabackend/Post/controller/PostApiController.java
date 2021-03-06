package com.example.woowabackend.Post.controller;

import static com.example.woowabackend.Post.controller.dto.PostResponseDto.*;
import static com.example.woowabackend.member.controller.SessionConst.*;

import com.example.woowabackend.Post.controller.dto.PostListResponseDto;
import com.example.woowabackend.Post.controller.dto.PostPageDto;
import com.example.woowabackend.Post.controller.dto.PostResponseDto;
import com.example.woowabackend.Post.domain.Board;
import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostTag;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.Post.repository.PostTagRepository;
import com.example.woowabackend.Post.repository.TagRepository;
import com.example.woowabackend.Post.service.FileSystemStorageService;
import com.example.woowabackend.Post.service.PostLikeService;
import com.example.woowabackend.Post.service.PostService;
import com.example.woowabackend.Post.controller.dto.PostSaveRequestDto;
import com.example.woowabackend.Post.controller.dto.PostUpdateRequestDto;
import com.example.woowabackend.Post.service.PostTagService;
import com.example.woowabackend.comment.controller.dto.CommentListResponseDto;
import com.example.woowabackend.comment.repository.CommentRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import com.example.woowabackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;
    private final MemberRepository memberRepository;
    private final PostTagRepository postTagRepository;
    private final CommentRepository commentRepository;
    private final PostLikeService postLikeService;
    private final PostRepository postRepository;

    @PostMapping("/api/post")
    public PostCreateResponseDto postSave(@RequestBody PostSaveRequestDto requestDto,
                                      @RequestParam(value = "tags", defaultValue = "false") List<String> tags,
                                      @SessionAttribute(value = LOGIN_MEMBER, required = true) String userId){
        requestDto.setAuth(userId);
        Member member = memberRepository.findByUserId(userId).orElseThrow();

        PostResponseDto responseDto= new PostResponseDto();
        responseDto.setHashtag(tags);

        return postService.save(requestDto,tags,member);

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

    @GetMapping("/api/post/index")
    public PostPageDto index(@SessionAttribute(value = LOGIN_MEMBER, required = true) String userId,
                                           @PageableDefault(page =0, size = 5, sort = "id", direction = Sort.Direction.DESC)
                                                   Pageable pageable,String searchKeyword) {

        Page<Post> list = null;

        if (searchKeyword == null) {
            list = postService.findAll(pageable);
        } else {
            list = postService.postsSearchList(searchKeyword, pageable);
        }

        int Page = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(Page - 4, 1);
        int endPage = Math.min(Page + 5, list.getTotalPages());

        return PostPageDto.of(postService.findAll(pageable));
    }


    @GetMapping("/post/detail/{id}")
    public PostResponseDto postDetail(@PathVariable Long id, Long tagId, @SessionAttribute(value = LOGIN_MEMBER, required = true) String userId ){
        PostResponseDto dto = postService.findById(id);
        List<PostTag> postTags =postTagRepository.findByPostId(id);


        postService.updateView(id);
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
