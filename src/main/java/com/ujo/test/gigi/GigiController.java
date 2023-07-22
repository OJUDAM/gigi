package com.ujo.test.gigi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujo.test.gigi.service.CongestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GigiController {

    private final CongestionService congestionService;
    private final ObjectMapper objectMapper;


    @GetMapping("/")
    public String hello(Model model) {
        try {
            String congestionJsonArray = objectMapper.writeValueAsString(congestionService.getCongestionByCode("228"));

            model.addAttribute("congestions",congestionJsonArray);

            return "index";
        } catch (JsonProcessingException e) {
            //TODO: json 예외처리
            throw new RuntimeException(e);
        }
    }

}
