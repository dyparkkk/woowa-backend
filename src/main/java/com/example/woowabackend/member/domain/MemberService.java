package com.example.woowabackend.member.domain;
import com.example.woowabackend.member.domain.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(MemberInfoDto memberinfoDto) {

        validateDuplicateUser(memberinfoDto.getUserId()); // ID 중복 체크
        if(!memberinfoDto.getPw().equals(memberinfoDto.getUserPwdCheck())){    // pwd 체크
            throw new IllegalStateException("Password가 동일하지 않습니다.");
        }

        String encodePwd = passwordEncoder.encode(memberinfoDto.getPw());
        return memberRepository.save(memberinfoDto.toEntity(encodePwd)).getId();

    }

    @Transactional
    public Member loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberRepository.findByUserId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("not found userId : "+memberId));
    }

    // userId 중복검사
    private void validateDuplicateUser(String memberId){
        Optional<Member> member = memberRepository.findByUserId(memberId);
        log.info("userId : " + memberId);
        member.ifPresent(findUser -> {
            throw new IllegalStateException("아이디 중복");
        });
    }

    public Long login(LoginInfoDto loginInfoDto, HttpServletRequest request){
        Optional<Member> member = memberRepository.findByUserId(loginInfoDto.getUserId());

        HttpSession session = request.getSession();

        check(loginInfoDto.getUserId(), loginInfoDto.getPw());

        session.setAttribute("user", loginInfoDto.getUserId());

        return 12L;
    }

    private boolean check(String userId, String userPwd){
        Optional<Member> memberWrapper = memberRepository.findByUserId(userId);
        Member member = memberWrapper.get();
        if (memberWrapper==null){
            log.info("해당 아이디가 존재하지 않습니다.");
            return false;
        }

        if(!passwordEncoder.matches(userPwd, member.getPw())){
            throw new BadCredentialsException("비밀번호가 틀립니다.");
        }
        return true;
    }
}
