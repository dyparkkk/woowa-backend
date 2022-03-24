package com.example.woowabackend.member.controller;

import com.example.woowabackend.member.exception.DuplicateUserIdException;
import com.example.woowabackend.member.exception.ErrorResponse;
import com.example.woowabackend.member.exception.PwNotMatchException;
import com.example.woowabackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.example.woowabackend.member.controller.dto.MemberDto.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService loginService;

    @PostMapping("/api/v1/signUp") // 회원가입
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto dto) {
        return loginService.signUp(dto);
    }


    @PostMapping("/api/v1/signIn")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return loginService.signIn(dto);
    }

}
