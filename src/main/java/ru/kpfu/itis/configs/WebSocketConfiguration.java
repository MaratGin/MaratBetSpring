package ru.kpfu.itis.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.kpfu.itis.security.handler.AuthHandshakeHandler;
import ru.kpfu.itis.security.handler.MessagesWebSocketHandler;

@Configuration
@EnableWebSocket
@ComponentScan
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired
    private MessagesWebSocketHandler messagesWebSocketHandler;

    @Autowired
    private AuthHandshakeHandler authHandshakeHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        System.out.println("config!");

        webSocketHandlerRegistry.addHandler(messagesWebSocketHandler, "/chatt")
                .setHandshakeHandler(authHandshakeHandler);
    }
}
