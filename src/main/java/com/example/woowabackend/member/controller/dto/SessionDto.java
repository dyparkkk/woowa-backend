package com.example.woowabackend.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

public class SessionDto {

    @Data
    @AllArgsConstructor
    public static class SessionInfoResponse {
        private String userId;
        private String sessionId;
        private Date createTime;
        private Date lastAccessTime;

    }
}
