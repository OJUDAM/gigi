package com.ujo.test.speculation.controller;

import com.ujo.test.speculation.service.RealTimeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequiredArgsConstructor
public class RealTimeController {

    private final RealTimeService realTimeService;

//    @GetMapping(value = "/", produces = "text/event-stream")
//    public SseEmitter showTime() {
//        return realTimeService.getChangedValue();
//    }
}
