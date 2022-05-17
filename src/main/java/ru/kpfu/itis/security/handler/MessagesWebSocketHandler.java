package ru.kpfu.itis.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.kpfu.itis.controllers.ChatController;
import ru.kpfu.itis.models.dtos.MessageDto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessagesWebSocketHandler extends TextWebSocketHandler {

    private static Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(MessagesWebSocketHandler.class);


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("Message handling!");
        HttpHeaders headers = session.getHandshakeHeaders();
        String messageAsString = (String) message.getPayload();
        MessageDto body = objectMapper.readValue(messageAsString, MessageDto.class);
        System.out.println(body.getFrom()+ "   /// " + body.getText());

        if (body.getText().equals("Присоединился!")) {
            sessions.put(body.getFrom(), session);
        }

        for (WebSocketSession currentSession : sessions.values()) {
            currentSession.sendMessage(new TextMessage(messageAsString));
        }
    }
}