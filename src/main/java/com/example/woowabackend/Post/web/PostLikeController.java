package com.example.woowabackend.Post.web;

import com.example.woowabackend.Post.service.PostLikeService;
import com.example.woowabackend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;

    @GetMapping("/like/{id}")
    public ResponseEntity<List<String>> getLikeCount(@PathVariable Long id, @RequestParam(defaultValue = "1") Member userid) {
        log.info("post-id : {} ", id);
        log.info("member : {} ", userid);

        List<String> resultData = postLikeService.count(id, userid);

        log.info("likeCount : {} ", resultData);

        return new ResponseEntity<>(resultData, HttpStatus.OK);
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
