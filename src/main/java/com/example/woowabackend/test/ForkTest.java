package com.example.woowabackend.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ForkTest {

    @GetMapping("/fork")
    public String test() {
        return "Fork Test!";
    }
}
