package com.example.woowabackend.domain.post;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.Post.controller.dto.PostSaveRequestDto;
import com.example.woowabackend.Post.controller.dto.PostUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    public void tearDown() throws Exception{
//        postRepository.deleteAll();
//    }
//
//    @Test
//    public void Post_등록된다() throws Exception {
//        //given
//        String title ="Test title2";
//        String content = "Test content2";
//        String auth = "woowa2";
//
//        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
//                .title(title)
//                .content(content)
//                .auth(auth)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/post";
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//        List<Post> all = postRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//    }
//
//    @Test
//    public void Post_수정된다() throws Exception {
//        //given
//        Post savedPost = postRepository.save(Post.builder()
//                .title("Test title")
//                .content("Test content")
//                .auth("auth")
//                .build());
//
//        Long updateId = savedPost.getId();
//        String expectedTitle = "Test title";
//        String expectedContent = "Test content";
//
//        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
//                .title(expectedTitle)
//                .content(expectedContent)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/post/" + updateId;
//
//        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Post> all = postRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
//    }
}
