package com.ujo.test.gigi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujo.test.gigi.dto.response.CongestionResponseDTO;
import com.ujo.test.gigi.dto.response.MetaInfoResponseDTO;
import com.ujo.test.gigi.entity.BaseStationEntity;
import com.ujo.test.gigi.entity.StatAndStationEntity;
import com.ujo.test.gigi.service.CongestionService;
import com.ujo.test.gigi.service.MetaInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GigiController {

    private final CongestionService congestionService;
    private final MetaInfoService metaInfoService;
    private final ObjectMapper objectMapper;


    @GetMapping("/")
    public String hello(Model model) {
        try {
            //지하철 통계 데이터 가져온 후 JsonArray 로 변환
            String congestionJsonArray = objectMapper.writeValueAsString(congestionService.getCongestionByCode("226"));

            //지하철 주요 역 정보 가져 온후 Entity -> DTO 변환
            List<BaseStationEntity> stationEntityList = metaInfoService.getPrimaryStation();
            List<MetaInfoResponseDTO> stationResponse =  stationEntityList.stream().map(MetaInfoResponseDTO::new).collect(Collectors.toList());

            //뷰에 전달
            model.addAttribute("congestions",congestionJsonArray);
            model.addAttribute("metaInfo", stationResponse);

            return "index";
        } catch (JsonProcessingException e) {
            //TODO: json 예외처리
            throw new RuntimeException(e);
        }
    }

    @ResponseBody
    @GetMapping("/congestion")
    public List<CongestionResponseDTO> getCongestion(String code) {

        List<StatAndStationEntity> statAndStationEntities = congestionService.getCongestionByCode(code);
        return statAndStationEntities.stream().map(CongestionResponseDTO::new).collect(Collectors.toList());
    }

}
