package com.example.woowabackend.comment.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.controller.dto.CommentSaveDto;
import com.example.woowabackend.comment.repository.CommentRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.woowabackend.comment.controller.dto.CommentSaveDto.*;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public SuccessResponseDto create(CommentSaveDto commentSaveDto, Long postId){
        Member member = memberRepository.findById(commentSaveDto.getMemberId()).orElseThrow(()->{
            return new IllegalArgumentException("User Id not found");
        });

        Post post = postRepository.findById(postId).orElseThrow(()->{
            return new IllegalArgumentException("Comment Create Fail");
        });

        Long parentId = 0L;

        Comment comment = Comment.builder()
                .member(member)
                .post(post)
                .parentId(parentId)
                .content(commentSaveDto.getContent())
                .build();

        commentRepository.save(comment);
        return new SuccessResponseDto();
    }

    @Transactional
    public SuccessResponseDto childCreate(CommentSaveDto commentSaveDto, Long postId, Long parentId){
        Member member = memberRepository.findById(commentSaveDto.getMemberId()).orElseThrow(()->{
            return new IllegalArgumentException("UserId not found");
        });

        Post post = postRepository.findById(postId).orElseThrow(()->{
            return new IllegalArgumentException("Comment Create Fail");
        });

        Comment comment = Comment.builder()
                .member(member)
                .post(post)
                .parentId(parentId)
                .content(commentSaveDto.getContent())
                .build();

        commentRepository.save(comment);
        return new SuccessResponseDto();
    }

    @Transactional
    public SuccessResponseDto delete(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new
                IllegalArgumentException("Comment Not found."));
        comment.update();
        return new SuccessResponseDto();
    }

    @Transactional
    public Long postDelete(Long postId){
        Comment comment = commentRepository.findById(postId).orElseThrow(() -> new
                IllegalArgumentException("Comment Not found."));
        comment.update();
        return postId;
    }
}
