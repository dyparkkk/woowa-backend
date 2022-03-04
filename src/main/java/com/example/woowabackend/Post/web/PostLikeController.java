package com.example.woowabackend.Post.web;

import com.example.woowabackend.Post.service.PostLikeService;
import com.example.woowabackend.Post.web.dto.PostResponseDto;
import com.example.woowabackend.Post.web.dto.PostSaveRequestDto;
import com.example.woowabackend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;

    @GetMapping("/like/{id}")
    public PostResponseDto getLikeCount(@PathVariable Long id,
                                                     @RequestBody PostSaveRequestDto dto,
                                                     @RequestParam("id") Long userid) {
        log.info("post-id : {} ", id);
        log.info("member : {} ", userid);

//        List<String> resultData = postLikeService.count(id, userid);

        log.info("likeCount : {} ", resultData);

        return new PostResponseDto();
    }


    @DeleteMapping("/like/{id}")
    public ResponseEntity<String> cancelLike(@RequestParam(defaultValue = "1")Member userid, @PathVariable Long id) {
        if (userid != null) {
            postLikeService.cancelLike(userid, id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping("/like/{id}")
    public ResponseEntity<String> addLike(@RequestParam(defaultValue = "1")Member userid, @PathVariable Long id) {
        boolean result = false;

        if (Objects.nonNull(userid))
            result = postLikeService.addLike(userid, id);

        return result ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
