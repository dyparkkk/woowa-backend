package com.example.woowabackend.member.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Birth {
    @Column(name = "birth")
    private String birth;

    public Birth() {
    }

    public Birth(String birth) {
        this.birth = birth;
    }
}
