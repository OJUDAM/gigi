package com.ujo.test.speculation.service;

import com.ujo.test.speculation.repository.ArrivalRealTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RealTimeService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final ArrivalRealTimeRepository arrivalRealTimeRepository;

//    public SseEmitter getChangedValue() {
//
//    }

    private void sendToClient(SseEmitter emitter, String id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            throw new RuntimeException("연결 오류!");
        }
    }
}
