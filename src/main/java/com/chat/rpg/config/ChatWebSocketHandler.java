package com.chat.rpg.config;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming messages and send responses
        String payload = message.getPayload();
        // Process message and generate a response
        String response = processMessage(payload);
        session.sendMessage(new TextMessage(response));
    }

    private String processMessage(String message) {
        // Placeholder for AI integration
        return "You said: " + message;
    }
}
