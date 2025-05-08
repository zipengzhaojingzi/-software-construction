package com.pet.demo.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.demo.entity.Msg;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
//@Component
@Service
public class RedisChatMemory implements ChatMemory {

    private final StringRedisTemplate redisTemplate;

    private final ObjectMapper objectMapper;

    private final static String PREFIX = "chat:";

    @Override
    public void add(String conversationId, List<Message> messages) {
        if (messages == null || messages.isEmpty()) {
            return;
        }
        List<String> list = messages.stream().map(Msg::new).map(msg -> {
            try {
                return objectMapper.writeValueAsString(msg);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()); // 替代.toList().;
        redisTemplate.opsForList().leftPushAll(PREFIX + conversationId, list);
        // 在 leftPushAll 后追加（示例设置30天过期）
        redisTemplate.expire(PREFIX + conversationId, 30, TimeUnit.DAYS);

    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        List<String> list = redisTemplate.opsForList().range(PREFIX + conversationId, 0, lastN);
        if (list == null || list.isEmpty()) {
            return List.of();
        }
        return list.stream().map(s -> {
            try {
                return objectMapper.readValue(s, Msg.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).map(Msg::toMessage).collect(Collectors.toList()); // 替代.toList();
    }

    @Override
    public void clear(String conversationId) {
        redisTemplate.delete(PREFIX + conversationId);
    }
}
