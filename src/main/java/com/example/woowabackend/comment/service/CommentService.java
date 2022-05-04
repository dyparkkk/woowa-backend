package com.example.woowabackend.comment.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.comment.controller.dto.CommentListResponseDto;
import com.example.woowabackend.comment.domain.Comment;
import com.example.woowabackend.comment.controller.dto.CommentSaveDto;
import com.example.woowabackend.comment.repository.CommentRepository;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<CommentListResponseDto> commentFindAll(Long postId, Pageable pageable) {
/*        List<CommentListResponseDto> test = commentRepository.findByPostId(postId, pageable).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
        log.info(String.valueOf(test));*/

/*        return commentRepository.findByPostId(postId, pageable).stream()
                .filter(h -> h.getDeleteYN().equals("N"))
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());*/
        return commentRepository.findByPostId(postId, pageable).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public SuccessResponseDto commentCreate(CommentSaveDto commentSaveDto, Long postId){
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
    public SuccessResponseDto childCommentCreate(CommentSaveDto commentSaveDto, Long postId, Long parentId){
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
    public SuccessResponseDto commentDelete(String userId, CommentSaveDto commentSaveDto){
        Comment comment = commentRepository.findById(commentSaveDto.getCommentId()).orElseThrow(() -> new
                IllegalArgumentException("Comment Not found."));
        if (userCheck(userId, comment.getMember().getId())){
            log.info("aaa");
        }
        comment.update();
        return new SuccessResponseDto();
    }

    //작성자가 작성한 댓글인지 확인
    private boolean userCheck(String userId, Long comment){
        return true;
    }

    @Transactional
    public Long postDelete(Long postId){
        Comment comment = commentRepository.findById(postId).orElseThrow(() -> new
                IllegalArgumentException("Comment Not found."));
        comment.update();
        return postId;
    }
}
