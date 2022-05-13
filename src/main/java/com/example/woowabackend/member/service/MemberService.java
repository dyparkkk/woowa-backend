package com.example.woowabackend.member.service;

import com.example.woowabackend.member.domain.Birth;
import com.example.woowabackend.member.domain.PhoneNumber;
import com.example.woowabackend.member.exception.DuplicateUserIdException;
import com.example.woowabackend.member.exception.PwNotMatchException;
import com.example.woowabackend.member.domain.Member;
import com.example.woowabackend.member.repository.MemberRepository;
import com.example.woowabackend.security.MyUserDetailsService;
import com.example.woowabackend.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

import static com.example.woowabackend.member.controller.dto.MemberDto.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailsService myUserDetailsService;

/*    @PostConstruct
    public void init() {
        String pw = passwordEncoder.encode("asd123");

        memberRepository.save(new Member("qqq@naver.com", pw, "박도영",
                new PhoneNumber("010-1234-1234"),
                new Birth("1996-11-18")));

    }*/

    @Transactional
    public Long signUp(SignUpRequestDto dto){ // 회원가입
        // id 중복체크 ( 리펙토링 대상)
        validateDuplicateUser(dto.getUserId());
        // pw 암호화
        String encodePw = passwordEncoder.encode(dto.getPw());

        // member 생성 후 저장
        return memberRepository
                .save(new Member(dto.getUserId(), encodePw, dto.getName(), dto.getHp(), dto.getBirth()))
                .getId();
    }

    @Transactional
    public String signIn(LoginRequestDto dto) {
        // findMember
        // 예외처리 문제 있음 ....filter라서
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(dto.getUserId());

        // pw 체크
        if(!passwordEncoder.matches(dto.getPw(), userDetails.getPassword())){
            throw new PwNotMatchException("userId : " + userDetails.getUsername() + " Invalid password");
        }

       return userDetails.getUsername();
    }

    // 유저 중복 확인
    public void validateDuplicateUser(String userId){
        memberRepository.findByUserId(userId)
                        .ifPresent(member -> {
                            log.debug("userId : {}, 아이디 중복 발생", userId);
                            throw new DuplicateUserIdException("아이디 중복");
                        });
    }
}
