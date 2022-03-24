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

import static com.example.woowabackend.member.controller.dto.MemberDto.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MyUserDetailsService myUserDetailsService;

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto dto){ // 회원가입
        // 중복체크
        validateDuplicateUser(dto.getUserId());
        String encodePw = passwordEncoder.encode(dto.getPw());

        memberRepository.save(Member.testCreate(dto.getUserId(), encodePw));
        return new SignUpResponseDto();

    }

    @Transactional
    public LoginResponseDto signIn(LoginRequestDto dto) {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(dto.getUserId());

        if(!passwordEncoder.matches(dto.getPw(), userDetails.getPassword())){
            throw new PwNotMatchException("userId : " + userDetails.getUsername() + " Invalid password");
        }

        Authentication authentication =  new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        return new LoginResponseDto("Bearer-"+jwtTokenProvider.createAccessToken(authentication));
    }

    private void validateDuplicateUser(String userId){
        memberRepository.findByUserId(userId)
                        .ifPresent(member -> {
                            log.debug("userId : {}, 아이디 중복으로 회원가입 실패", userId);
                            throw new DuplicateUserIdException("아이디 중복");
                        });
    }
}
