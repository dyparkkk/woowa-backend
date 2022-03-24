package com.example.woowabackend.Post.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.repository.PostLikeRepository;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.Post.web.dto.PostResponseDto;
import com.example.woowabackend.Post.web.dto.PostSaveRequestDto;
import com.example.woowabackend.Post.web.dto.PostUpdateRequestDto;
import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.repo.CommentRepository;
import com.example.woowabackend.member.domain.Member;
import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private Object Integer;

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        post.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostResponseDto findById(Long id){
        Post entity = postRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllDesc(){
        return postRepository.findAllDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }



    @Transactional
    public Long delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        post.delete();

        List<Comment> comment = commentRepository.findByPostId(id);

        for(int i=0; i<comment.size(); i++){
            comment.get(i).update();
        }

        return id;

    }

    //조회수
    public void updateView(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.increaseViewCnt();
        postRepository.save(post);
    }


}
