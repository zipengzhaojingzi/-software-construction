//package com.pet.demo.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
////@RequiredArgsConstructor
//@RestController
//@RequestMapping("/ai")
//@CrossOrigin(origins = "*")  // 启用CORS
//public class ChatController {
//
//    private  final ChatClient chatClient;
//    public ChatController(ChatClient.Builder builder) {
//        this.chatClient = builder.build();
//    }
//    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
//    public Flux<String> chat(String prompt) {
//        return chatClient.prompt().user(prompt)
//                .stream().content();
//    }
//}