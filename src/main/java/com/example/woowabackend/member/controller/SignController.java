package com.example.woowabackend.member.controller;

import com.example.woowabackend.member.controller.dto.SignInResponseDto;
import com.example.woowabackend.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final LoginService loginService;

    @PostMapping("/api/v1/signUp")
    public Long signUp(@RequestParam String id,
                       @RequestParam String pw) {
        return loginService.signUp(id, pw);
    }



    @PostMapping("/api/v1/signIn")
    public SignInResponseDto signInp(@RequestParam String id,
                                     @RequestParam String pw) {
        return loginService.signIn(id, pw);
    }
}