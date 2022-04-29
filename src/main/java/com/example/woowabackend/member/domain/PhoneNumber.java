package com.example.woowabackend.member.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable @Getter
public class PhoneNumber {

    @Column(name = "phone_number")
    private String num;

    public PhoneNumber() {
    }

    public PhoneNumber(String num) {
        this.num = num;
    }




}
