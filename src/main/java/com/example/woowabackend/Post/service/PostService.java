package com.example.woowabackend.Post.service;

import com.example.woowabackend.Post.controller.dto.PostResponseDto;
import com.example.woowabackend.Post.controller.dto.PostListResponseDto;
import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostTag;
import com.example.woowabackend.Post.domain.Tag;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.Post.repository.PostTagRepository;
import com.example.woowabackend.Post.repository.TagRepository;
import static com.example.woowabackend.Post.controller.dto.PostResponseDto.*;
import com.example.woowabackend.Post.controller.dto.PostSaveRequestDto;
import com.example.woowabackend.Post.controller.dto.PostUpdateRequestDto;
import com.example.woowabackend.comment.controller.dto.CommentListResponseDto;
import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.repository.CommentRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    //post 저장
    @Transactional
    public PostCreateResponseDto save(PostSaveRequestDto requestDto, List<String> tagName,Member member) {
        requestDto.setMember(member);
        Post post = requestDto.toEntity();
        postRepository.save(post);

        //태그 생성
        for (String name : tagName) {
            Tag tag = tagRepository.findByName(name);
            if (tag == null) {
                Tag newHashTag = new Tag(name);
                newHashTag.setName(name);
                tag = tagRepository.save(newHashTag);

                postTagRepository.save(new PostTag(tag,post));
            }
        }
        return new PostCreateResponseDto();
    }

    //post 수정
    @Transactional
    public PostUpdateResponseDto update (Long id, PostUpdateRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        post.update(requestDto.getTitle(), requestDto.getContent());
        return new PostUpdateResponseDto();
    }

    @Transactional
    public PostResponseDto findById (Long id){
        Post entity = postRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostResponseDto(entity);
    }

    //조회수
    @Transactional
    public void updateView (Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.increaseViewCnt();
        postRepository.save(post);
    }

    public Iterable<Post> findAllByTag (String tagName) {
        return postRepository.findAll().stream()
                .filter(post -> post.Tag(tagName))
                .collect(Collectors.toList());
    }

    //post 삭제
    @Transactional
    public PostDeleteResponseDto delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        //태그 삭제
        List<PostTag> postTags = post.getPostTags();
        for(PostTag eachPostTag : postTags){
            if(eachPostTag.getTag().getPostTags().size()==1) tagRepository.delete(eachPostTag.getTag());
            postTagRepository.delete(eachPostTag);
        }

        post.delete();

        Pageable pageable = null;
        Page<Comment> comment = commentRepository.findByPostId(id, pageable);
        List<Comment> listComment = comment.getContent();
        for(int i=0; i<comment.getTotalElements(); i++){
            listComment.get(i).update();
        }
        return new PostDeleteResponseDto();
    }

    //post 조회
    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new) // (post) -> {PostListResponseDto(post)}
                .collect(Collectors.toList());  // stream -> List

    }

    public List<Tag> findTags () {
        return postRepository.findTags();
    }
}

