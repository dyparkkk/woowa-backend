package com.example.woowabackend.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ForkTest {


    // 주석  추가 다 헤보자
    // aaaaa
    @GetMapping("/fork")
    public String test() {
        return "Fork Test!";
    }
}
