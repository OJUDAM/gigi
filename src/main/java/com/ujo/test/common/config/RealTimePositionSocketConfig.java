package com.ujo.test.common.config;

import com.ujo.test.speculation.socket.RealTimeSocketHandler;
import com.ujo.test.speculation.socket.RealTimeSocketPositionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration

@RequiredArgsConstructor
public class RealTimePositionSocketConfig implements WebSocketConfigurer {

    private final RealTimeSocketPositionHandler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/socket/realTime/position").setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor());   // interceptor for adding httpsession into websocket session

    }
}
