package com.example.woowabackend.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/test")
public class TestApi {

    @GetMapping("/hi")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/query-string/login")
    public ResponseForm testLogin(@Validated @RequestParam String userId,@Validated @RequestParam String pw) {
        Content content = new Content(userId, pw);
        return new ResponseForm(true, "/test/query-string/login", content);
    }

    @PostMapping("/json/login")
    public ResponseForm testJsonLogin(@Validated @RequestBody Content content) {
        return new ResponseForm(true, "/test/json/login", content);
    }

    @Data
    public static class ResponseForm{
        private boolean success;
        private String message;
        private Content content;

        public ResponseForm(boolean success, String message, Content content) {
            this.success = success;
            this.message = message;
            this.content = content;
        }
    }

    @Data
    @NoArgsConstructor
    private static class Content{
        @NotNull
        private String userId;
        @NotNull
        private String pw;

        public Content(String userId, String pw) {
            this.userId = userId;
            this.pw = pw;
        }
    }
}
