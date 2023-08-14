package com.ujo.gigi.common.config;

import com.ujo.gigi.speculation.socket.RealTimeSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * 분당선 실시간 도착 정보 소켓 설정
 * */
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
