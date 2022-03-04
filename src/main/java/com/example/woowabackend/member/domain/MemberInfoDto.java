package com.example.woowabackend.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberInfoDto {
    private String userId;
    private String pw;
    private String name;
    private String phoneNumber;
    private String birth;
    private String userPwdCheck;

    @Builder
    public MemberInfoDto(String userId, String pw, String name, String phoneNumber,String birth, String userPwdCheck) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.userPwdCheck = userPwdCheck;
    }

    public Member toEntity(String bcryptPwd){
        return  Member.builder()
                .userId(userId)
                .pw(bcryptPwd)
                .name(name)
                .build();
    }
}
