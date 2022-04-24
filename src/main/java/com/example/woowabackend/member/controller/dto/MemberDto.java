package com.example.woowabackend.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpRequestDto{ // validation 추가 예정

        @Email
        @NotBlank
        private String userId;
        @NotBlank
        private String pw;
        @NotBlank
        private String name;
        private String hp;
        private String birth;
    }

    @Data
    @NoArgsConstructor
    public static class SignUpResponseDto {
        private final Boolean success = true;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequestDto{
        @Email
        @NotBlank
        private String userId;
        @NotBlank
        private String pw;
    }

    @Data
    @AllArgsConstructor
    public static class LoginResponseDto{
        private final Boolean success = true;
    }
}
