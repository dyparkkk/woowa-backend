package com.example.woowabackend.member.service;

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

    @Transactional
    public Long signUp(SignUpRequestDto dto){ // 회원가입
        // id 중복체크 ( 리펙토링 대상)
        validateDuplicateUser(dto.getUserId());
        // pw 암호화
        String encodePw = passwordEncoder.encode(dto.getPw());

        // member 생성 후 저장
        Member member = memberRepository.save(Member.testCreate(dto.getUserId(), encodePw));
        return member.getId();
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

    // 회원가입할 때 유저 중복 확인
    private void validateDuplicateUser(String userId){
        memberRepository.findByUserId(userId)
                        .ifPresent(member -> {
                            log.debug("userId : {}, 아이디 중복으로 회원가입 실패", userId);
                            throw new DuplicateUserIdException("아이디 중복");
                        });
    }
}
