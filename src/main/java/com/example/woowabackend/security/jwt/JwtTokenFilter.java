package com.example.woowabackend.security.jwt;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.woowabackend.security.jwt.JwtTokenProvider.*;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request, AUTHORIZATION_HEADER);

        log.info("--------- in jwtTokenFilter -----jwt:{}, ", jwt); // test

        if (jwt != null && jwtTokenProvider.validateToken(jwt)== JwtCode.ACCESS) {
            Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("set Authentication to security context for '{}', uri: {}", authentication.getName(), request.getRequestURI());
        }
        else {
            log.info("no valid JWT token found, uri: {}", request.getRequestURI());
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request, String header) {
        String bearerToken = request.getHeader(header);
        if (bearerToken != null && bearerToken.startsWith("Bearer-")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
