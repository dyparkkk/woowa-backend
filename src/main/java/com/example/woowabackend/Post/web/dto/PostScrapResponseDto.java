package com.example.woowabackend.Post.web.dto;

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
}
