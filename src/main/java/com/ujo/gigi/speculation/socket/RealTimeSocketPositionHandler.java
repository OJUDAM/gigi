package com.ujo.gigi.speculation.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujo.gigi.speculation.service.RealTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class RealTimeSocketPositionHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();
    static Boolean runCheck = false;
    private final RealTimeService realTimeService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        log.info("payload : " + payload);

        Iterator<WebSocketSession> itr = list.iterator();
        while (itr.hasNext()) {
            try {
                WebSocketSession webSocketSession = itr.next();
                webSocketSession.sendMessage(new TextMessage(getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // connection established
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);

        session.sendMessage(new TextMessage(getData()));
    }

    // connection closed
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        list.remove(session);
    }

    private String getData(){
        try {
            Hashtable<String, Object> map = new Hashtable<>();
            map.put("data", realTimeService.getArrivalTimePositionList());
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
