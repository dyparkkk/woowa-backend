package com.example.woowabackend.domain.post;


import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
//        String title = "Test";
//        String content ="Test 입니다.";
//
//        postRepository.save(Post.builder()
//                .title(title)
//                .content(content)
//                .auth("woowa")
//                .build());
//
//        //when
//        List<Post> postList = postRepository.findAll();
//
//        //then
//        Post post = postList.get(0);
//        assertThat(post.getTitle()).isEqualTo(title);
//        assertThat(post.getContent()).isEqualTo(content);
    }
}
