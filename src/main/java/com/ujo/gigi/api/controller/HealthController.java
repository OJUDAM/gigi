package com.ujo.gigi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 서비스가 실행 중인 지 확인하는 Controller
 * */
@RestController
public class HealthController {

    @GetMapping("/health")
    public String checkHealth(){
        return "SUCCESS";
    }
}
