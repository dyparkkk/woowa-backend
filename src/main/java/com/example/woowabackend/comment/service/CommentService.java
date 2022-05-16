package com.example.woowabackend.comment.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.comment.controller.dto.CommentListResponseDto;
import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.controller.dto.CommentSaveDto;
import com.example.woowabackend.comment.domain.CommentResponse;
import com.example.woowabackend.comment.repository.CommentRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // Comment 조회
    @Transactional(readOnly = true)
    public ResponseEntity<CommentResponse> commentFindAll(Long postId, Pageable pageable) {

        Page<Comment> page = commentRepository.findByPostId(postId, pageable);

        List<CommentListResponseDto> commentList = commentRepository.findByPostId(postId, pageable).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
        
        CommentResponse commentResponse = CommentResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .result(commentList)
                .nowPage(page.getNumber())
                .lastPage(page.getTotalPages() -1)
                .build();

        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }


    // 댓글 작성
    @Transactional
    public ResponseEntity commentCreate(String userId, CommentSaveDto commentSaveDto, Long postId){

        Member member = memberRepository.findByUserId(userId).orElseThrow(()->{
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

        post.increaseCommentCnt();

        commentRepository.save(comment);
        return new ResponseEntity(HttpStatus.OK.value(), HttpStatus.OK);
    }

    // 대댓글 작성
    @Transactional
    public ResponseEntity childCommentCreate(String userId, CommentSaveDto commentSaveDto, Long postId, Long parentId){

        Member member = memberRepository.findByUserId(userId).orElseThrow(()->{
            return new IllegalArgumentException("User Id not found");
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

        post.increaseCommentCnt();

        commentRepository.save(comment);
        return new ResponseEntity(HttpStatus.OK.value(), HttpStatus.OK);
    }

    // 댓글 삭제
    @Transactional
    public ResponseEntity commentDelete(String userId, CommentSaveDto commentSaveDto, Long postId){
        Comment comment = commentRepository.findById(commentSaveDto.getCommentId()).orElseThrow(() -> new
                IllegalArgumentException("Comment Not found."));

        Post post = postRepository.findById(postId).orElseThrow(()->{
            return new IllegalArgumentException("Comment Delete Fail");
        });

        if (userCheck(userId, comment.getMember().getUserId())) {
            post.deleteCommentCnt();
            comment.update();
            return new ResponseEntity(HttpStatus.OK.value(), HttpStatus.OK);
        }

    return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }

    //작성자가 작성한 댓글인지 확인
    private boolean userCheck(String loginUser, String writeUser){
        if(loginUser.equals(writeUser)) {
            return true;
        }
        return false;
    }

    @Transactional
    public Long postDelete(Long postId){
        Comment comment = commentRepository.findById(postId).orElseThrow(() -> new
                IllegalArgumentException("Comment Not found."));
        comment.update();
        return postId;
    }
}
