package com.example.woowabackend.member.controller;

import lombok.RequiredArgsConstructor;
import com.example.woowabackend.member.domain.SessionConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.woowabackend.member.controller.dto.MemberDto.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService loginService;

    @RequestMapping(value = "/api/v1/signup", method = {RequestMethod.GET, RequestMethod.POST}) // 회원가입
    public SignUpResponseDto signUp(@Validated @RequestBody SignUpRequestDto dto) {
        loginService.signUp(dto);
        return new SignUpResponseDto();
    }

    @PostMapping("/api/v1/signin")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto,
                                  HttpServletRequest req) {
        String userId = loginService.signIn(dto);

        // 세션 생성
        HttpSession session = req.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, userId);

        return new LoginResponseDto();
    }

}
