package com.example.woowabackend.member.controller;

import com.example.woowabackend.member.domain.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class TestController {

    // @Secured("ROLE_AUTH")
//    @GetMapping("/auth/test")
    public String test(@AuthenticationPrincipal UserDetails userDetails) { //
        log.info("userDetails.getUsername : {} , userDetails.getAuthorities : {} "
                , userDetails.getUsername(), userDetails.getAuthorities());
        log.info("-------/auth/test");
        return "hi";    
    }

    @GetMapping("/api/test1")
    public String session1(@SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = true) String userId) { //
        log.info("userId ={}", userId);
        return "hi";
    }

//    @GetMapping("/auth/test")
    public String session2(HttpSession httpSession) { //
        String userId = (String) httpSession.getAttribute(SessionConst.LOGIN_MEMBER);
        return "hi";
    }
}
