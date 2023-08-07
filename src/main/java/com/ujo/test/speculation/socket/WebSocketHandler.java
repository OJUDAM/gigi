package com.ujo.test.speculation.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujo.test.speculation.service.RealTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
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
public class WebSocketHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();
    static Boolean runCheck = false;
    private final RealTimeService realTimeService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        log.info("payload : " + payload);

    }

    // connection established
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);

        if (!runCheck) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Iterator<WebSocketSession> itr = list.iterator();
                    while (itr.hasNext()) {
                        try {
                            WebSocketSession session = itr.next();
                            session.sendMessage(new TextMessage(String.valueOf(getData())));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            runCheck = true;
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(task, 0, 4 * 1000);
        }
    }

    // connection closed
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        list.remove(session);
    }

    private String getData(){
        try {
            Hashtable<String, Object> map = new Hashtable<>();
            map.put("data", realTimeService.getArrivalTimeList());
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
