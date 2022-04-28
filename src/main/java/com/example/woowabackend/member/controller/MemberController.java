package com.example.woowabackend.member.controller;

import com.example.woowabackend.comment.controller.dto.CommentSaveDto;
import com.example.woowabackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.woowabackend.member.controller.dto.MemberDto.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class MemberController {

    private final MemberService loginService;

    @RequestMapping(value = "/signup", method = {RequestMethod.GET, RequestMethod.POST}) // 회원가입
    public SignUpResponseDto signUp(@Validated @RequestBody SignUpRequestDto dto) {
        loginService.signUp(dto);
        return new SignUpResponseDto();
    }

    @PostMapping("/login")
    public SuccessResponseDto login(@Validated @RequestBody LoginRequestDto dto,
                                  HttpServletRequest req) {
        String userId = loginService.signIn(dto);

        // 세션 생성
        HttpSession session = req.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, userId);

        return new SuccessResponseDto();
    }

    @GetMapping("/signup/userid")
    public SuccessResponseDto userIdCheck(@Validated @RequestBody UserIdCheckDto dto) {
        loginService.validateDuplicateUser(dto.getUserId());
        return new SuccessResponseDto();
    }

    @PostMapping("/logout")
    public SuccessResponseDto logout(HttpServletRequest req) {

        HttpSession session = req.getSession(false);
        if (session == null) {
            throw new IllegalStateException("JSESSIONID를 확인할 수 없습니다.");
        }
        session.invalidate();
        return new SuccessResponseDto();
    }
}
