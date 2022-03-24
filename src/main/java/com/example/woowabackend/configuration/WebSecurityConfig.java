//package com.example.woowabackend.configuration;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//@RequiredArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .authorizeRequests()
//                .antMatchers("/","/**").access("permitAll")
//                .antMatchers("/h2-console/**").permitAll() // 추가
//                .and()
//                .csrf() // 추가
//                .ignoringAntMatchers("/h2-console/**").disable() // 추가
//                .httpBasic();
//    }
//
//    // Security 무시하기
//    public void configure(WebSecurity web)throws Exception{
//        web.ignoring().antMatchers("/h2-console/**");
//    }
//}
