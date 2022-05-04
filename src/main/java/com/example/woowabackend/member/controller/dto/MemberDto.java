package com.example.woowabackend.member.controller.dto;

import com.example.woowabackend.member.domain.Birth;
import com.example.woowabackend.member.domain.PhoneNumber;
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

        private PhoneNumber hp;

        private Birth birth;
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
    public static class SuccessResponseDto{
        private final Boolean success = true;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserIdCheckDto{
        @Email
        @NotBlank
        private String userId;
    }
}
