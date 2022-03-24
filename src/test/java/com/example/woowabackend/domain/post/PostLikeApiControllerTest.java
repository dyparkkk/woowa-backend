package com.example.woowabackend.domain.post;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostLike;
import com.example.woowabackend.Post.repository.PostLikeRepository;
import com.example.woowabackend.Post.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostLikeApiControllerTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PostLikeRepository postLikeRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PostRepository postRepository;

    protected Post addPost() {
        Post post = Post.builder()
                .title("Test title")
                .content("Test content")
                .auth("auth")
                .build();

        Post save = postRepository.save(post);

        return save;
    }
    @DisplayName("좋아요 테스트")
    @Test
    void testCreateLike() throws Exception {
        Post post = addPost();

        mockMvc.perform(post("/like/"+post.getId()))
                .andExpect(status().isOk());

        PostLike postlike = postLikeRepository.findAll().get(0);

        assertNotNull(postlike);
        assertNotNull(postlike.getMember().getId());
        assertNotNull(postlike.getPost().getId());
    }


    @DisplayName("좋아요 중복 테스트")
    @Test
    void testDuplicateLike() throws Exception {
        Post post  = addPost();

        mockMvc.perform(post("/like/"+post.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(post("/like/"+post.getId()))
                .andExpect(status().isBadRequest());

        PostLike postlike = postLikeRepository.findAll().get(0);

        assertNotNull(postlike);
        assertNotNull(postlike.getMember().getId());
        assertNotNull(postlike.getPost().getId());
    }


}
