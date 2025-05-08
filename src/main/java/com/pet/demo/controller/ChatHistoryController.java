package com.pet.demo.controller;

import com.pet.demo.entity.MessageVO;
import com.pet.demo.service.Impl.RedisChatMemory;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatHistoryController {


    private final RedisChatMemory chatMemory;
//
//    /**
//     * 查询会话历史列表
//     * @param type 业务类型，如：chat,service,pdf
//     * @return chatId列表
//     */
//    @GetMapping("/{type}")
//    public List<String> getChatIds(@PathVariable("type") String type) {
//        return chatHistoryRepository.getChatIds(type);
//    }
//
//    /**
//     * 根据业务类型、chatId查询会话历史
//     * @param type 业务类型，如：chat,service,pdf
//     * @param chatId 会话id
//     * @return 指定会话的历史消息
//     */
    @GetMapping("/chat/history")
    public List<MessageVO> getChatHistory( @RequestParam("userId") String chatId) {
//        System.out.println("=== 收到请求 ===");
//        System.out.println("参数类型：" + chatId.getClass().getSimpleName()); // 输出参数类型
//        System.out.println("参数值：" + chatId);

        List<Message> messages = chatMemory.get(chatId, Integer.MAX_VALUE);
//        System.out.println("从Redis获取到记录数：" + (messages != null ? messages.size() : 0));
        return messages.stream().map(MessageVO::new).collect(Collectors.toList()); // 替代.toList()
    }
}
