package com.example.woowabackend.member.controller;

import com.example.woowabackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.example.woowabackend.member.controller.dto.MemberDto.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService loginService;

    @RequestMapping(value = "/api/v1/signup", method = {RequestMethod.GET, RequestMethod.POST}) // 회원가입
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto dto) {
        return loginService.signUp(dto);
    }

    @PostMapping("/api/v1/signin")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return loginService.signIn(dto);
    }

}
