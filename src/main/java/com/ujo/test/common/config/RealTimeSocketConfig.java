package com.ujo.test.common.config;

import com.ujo.test.speculation.socket.RealTimeSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration

@RequiredArgsConstructor
public class RealTimeSocketConfig implements WebSocketConfigurer {

    private final RealTimeSocketHandler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/socket/realTime").setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor());   // interceptor for adding httpsession into websocket session

    }
}
