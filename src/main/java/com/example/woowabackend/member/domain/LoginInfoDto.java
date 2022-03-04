package com.example.woowabackend.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
@NoArgsConstructor
public class LoginInfoDto {
    private String userId;
    private String pw;

    @Builder
    public LoginInfoDto(String userId, String pw) {
        this.userId = userId;
        this.pw = pw;
    }

    public Member toEntity(){
        return  Member.builder()
                .userId(userId)
                .pw(pw)
                .build();
    }
}
