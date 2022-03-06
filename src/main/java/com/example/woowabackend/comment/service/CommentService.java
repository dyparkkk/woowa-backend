package com.example.woowabackend.comment.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.repo.PostRepository;
import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.dto.CommentSaveDto;
import com.example.woowabackend.comment.repo.CommentRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void create(CommentSaveDto commentSaveDto){
        Member member = memberRepository.findById(commentSaveDto.getMemberId()).orElseThrow(()->{
            return new IllegalArgumentException("User Id not found");
        });

        Post post = postRepository.findById(commentSaveDto.getPostId()).orElseThrow(()->{
            return new IllegalArgumentException("Comment Create Fail");
        });

        Comment comment = Comment.builder()
                .member(member)
                .post(post)
                .content(commentSaveDto.getContent())
                .likeCnt(0L)
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void childCreate(CommentSaveDto commentSaveDto){
        Member member = memberRepository.findById(commentSaveDto.getMemberId()).orElseThrow(()->{
            return new IllegalArgumentException("UserId not found");
        });

        Post post = postRepository.findById(commentSaveDto.getPostId()).orElseThrow(()->{
            return new IllegalArgumentException("Comment Create Fail");
        });

        Comment parentId = commentRepository.findById(commentSaveDto.getParentId()).orElseThrow(()->{
            return new IllegalArgumentException("CommentId not found");
        });

        Comment comment = Comment.builder()
                .member(member)
                .post(post)
                .content(commentSaveDto.getContent())
                .parent(parentId)
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long commentId){
        commentRepository.deleteById(commentId);
    }
}
