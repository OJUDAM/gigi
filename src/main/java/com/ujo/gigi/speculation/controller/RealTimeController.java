package com.ujo.gigi.speculation.controller;

import com.ujo.gigi.speculation.service.RealTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RealTimeController {

    private final RealTimeService realTimeService;

//    @GetMapping(value = "/", produces = "text/event-stream")
//    public SseEmitter showTime() {
//        return realTimeService.getChangedValue();
//    }
}
