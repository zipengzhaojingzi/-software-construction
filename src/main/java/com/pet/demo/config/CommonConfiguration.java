//package com.pet.demo.config;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.openai.OpenAiChatModel;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CommonConfiguration {
//
//
//    @Bean
//    public ChatClient chatClient(OpenAiChatModel model) {
//        return ChatClient.builder(model)
//                .defaultSystem("你是一个宠物专家")
//                .build();
//
//    }
//}
//
