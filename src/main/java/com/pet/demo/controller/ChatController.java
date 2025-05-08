package com.pet.demo.controller;

import com.pet.demo.service.Impl.RedisChatMemory;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")  // 启用CORS
public class ChatController {

    private  final ChatClient chatClient;
    private final RedisChatMemory redisChatMemory;

//    @RequestMapping(value = "/chat/{id}", produces = "text/html;charset=utf-8")
//    public Flux<String> chat(@PathVariable(name = "id") String id, String prompt) {
//        // 调用ChatClient获取响应
//        Flux<String> responseFlux = chatClient.prompt().user(prompt).stream().content();
//
//        // 异步保存完整对话记录
//        responseFlux.collectList()
//                .subscribe(parts -> {
//                    String fullResponse = String.join("", parts);
//                    List<Message> messages = List.of(
//                            new UserMessage(prompt),
//                            new AssistantMessage(fullResponse)
//                    );
//                    redisChatMemory.add(id, messages);
//                });
//
//        return responseFlux;
//
//    }
@PostMapping("/chat/{uid}/prompt={prompt}")
public Flux<String> chat(
        @PathVariable String uid,
        @PathVariable String prompt
) {
    Flux<String> responseFlux = chatClient.prompt()
            .user(prompt)
            .stream()
            .content();

    responseFlux.collectList()
            .subscribe(parts -> {
                String fullResponse = String.join("", parts);
                List<Message> messages = List.of(
                        new UserMessage(prompt),
                        new AssistantMessage(fullResponse)
                );
                redisChatMemory.add(uid, messages);
            });

    return responseFlux;
}
}