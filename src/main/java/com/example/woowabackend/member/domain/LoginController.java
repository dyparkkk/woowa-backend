package com.example.woowabackend.member.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/api/login")
    public String requestLogin( String userId, String pw){

        class Res{
            String id;
            String pwd;
        }
        Res res = new Res();
        res.id = userId;
        res.pwd = pw;

        Long answer = 123L;

        return "hi";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("login", new LoginInfoDto());
        return "login";
    }
}
