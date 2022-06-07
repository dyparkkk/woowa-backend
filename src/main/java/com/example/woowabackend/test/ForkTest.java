package com.example.woowabackend.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ForkTest {


    // 주석  추가
    @GetMapping("/fork")
    public String test() {
        return "Fork Test!";
    }
}
