package com.example.woowabackend.Post.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
public class PostScrapResponseDto {
    public boolean status;
    public String data;
    public Object object;




    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    @Data
    @AllArgsConstructor
    public static class ScrapAddResponseDto {
        private final Boolean success = true;

    }

    @Data
    @AllArgsConstructor
    public static class ScrapRemoveResponseDto {
        private final Boolean success = true;

    }
}
