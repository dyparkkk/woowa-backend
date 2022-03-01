package com.example.woowabackend.util.redis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;

@SpringBootTest
public class RedisTest {

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Test
//    void testStrings(){
//        //givne
//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        String key = "stringKey";
//
//        //when
//        valueOperations.set(key, "hello");
//
//        //then
//        String value = valueOperations.get(key);
//        Assertions.assertThat(value).isEqualTo("hello");
//
//        //after
//        redisTemplate.delete(key);
//    }
//
//    @Test
//    void testSet(){
//        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
//        String key = "setKey";
//
//        //when
//        setOperations.add(key, "h", "o", "l", "l", "e");
//
//        //then
//        Set<String> members = setOperations.members(key);
//        Long size = setOperations.size(key);
//
//        Assertions.assertThat(members).containsOnly("h","e","l", "o");
//        Assertions.assertThat(size).isEqualTo(4);
//
//        //after
//        redisTemplate.delete(key);
//    }

}
