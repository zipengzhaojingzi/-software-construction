package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.*;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Msg {
    MessageType messageType;
    String text;
    Map<String, Object> metadata;

    public Msg(Message message) {
        this.messageType = message.getMessageType();
        this.text = message.getText();
        this.metadata = message.getMetadata();
    }

    public Message toMessage() {
        switch (messageType) {
            case SYSTEM:
                return new SystemMessage(text);
            case USER:
                return new UserMessage(text, List.of(), metadata);
            case ASSISTANT:
                return new AssistantMessage(text, metadata, List.of(), List.of());
            default:
                throw new IllegalArgumentException("Unsupported message type: " + messageType);
        }
    }

}
