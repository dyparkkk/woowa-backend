package com.example.woowabackend.comment.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.comment.controller.dto.ChildListResponseDto;
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
import java.util.stream.Collectors;

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

        long parentId = 0;

        Page<Comment> page = commentRepository.findByPostId(postId, pageable);

        // 대댓글만 조회
        List<ChildListResponseDto> childCommentList = commentRepository.findByPostId(postId).stream()
                .filter(p -> p.getParentId() != 0)
                .map(ChildListResponseDto::new)
                .collect(Collectors.toList());

        // 댓글(부모 댓글)만 조회
        List<CommentListResponseDto> commentList = commentRepository.findByPostIdAndParentId(postId, parentId, pageable).stream()
                .map(n -> new CommentListResponseDto(n, childCommentList))
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

    // 댓글 수정
    @Transactional
    public ResponseEntity commentUpdate(String userId, CommentSaveDto commentSaveDto){

        Comment comment = commentRepository.findById(commentSaveDto.getCommentId()).orElseThrow(() -> new
                IllegalArgumentException("Comment Not found."));

        if (userCheck(userId, comment.getMember().getUserId())) {
            comment.update(commentSaveDto.getContent());
            return new ResponseEntity(HttpStatus.OK.value(), HttpStatus.OK);
        }

        return new ResponseEntity("you are not writer", HttpStatus.BAD_REQUEST);
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
