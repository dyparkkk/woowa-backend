package com.example.woowabackend.security.jwt;

import com.example.woowabackend.security.MyUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
@Slf4j
public class JwtTokenProvider implements InitializingBean {

    private final MyUserDetailsService myUserDetailsService;
    private final String secretKey;
    private final long tokenValidityInMs;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
                            @Value("${jwt.token-validity-in-sec}") long tokenValidity,
                            MyUserDetailsService myUserDetailsService){
        this.secretKey = secretKey;
        this.tokenValidityInMs = tokenValidity * 1000;
        this.myUserDetailsService = myUserDetailsService;
    }

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {  // init()
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(encodedKey.getBytes());
        // https://budnamu.tistory.com/entry/JWT 참고
    }

    public String createAccessToken(Authentication authentication) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMs);

        return Jwts.builder()
                .setSubject(authentication.getName()) //
                .setIssuedAt(now) // 발행시간
//                .claim(AUTHORITIES_KEY, authorities) // 권한
                .signWith(key, SignatureAlgorithm.HS512) // 암호화
                .setExpiration(validity) // 만료
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    public JwtCode validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return JwtCode.ACCESS;
        } catch (ExpiredJwtException e){
            // refresh token 확인 작업
            return JwtCode.EXPIRED;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("jwtException : {}", e);
        }
        return JwtCode.DENIED;
    }

    public static enum JwtCode{
        DENIED,
        ACCESS,
        EXPIRED;
    }
}