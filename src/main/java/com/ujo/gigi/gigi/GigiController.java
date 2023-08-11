package com.ujo.gigi.gigi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujo.gigi.gigi.dto.request.ArrivalTimeRequestDTO;
import com.ujo.gigi.gigi.dto.response.CongestionAndExitCountResponseDTO;
import com.ujo.gigi.gigi.dto.response.CongestionResponseDTO;
import com.ujo.gigi.gigi.dto.response.MetaInfoResponseDTO;
import com.ujo.gigi.entity.BundangLineEntity;
import com.ujo.gigi.entity.CongestionCountEntity;
import com.ujo.gigi.entity.StatAndStationEntity;
import com.ujo.gigi.gigi.service.ArrivalService;
import com.ujo.gigi.gigi.service.CongestionService;
import com.ujo.gigi.gigi.service.MetaInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GigiController {

    private final CongestionService congestionService;
    private final MetaInfoService metaInfoService;
    private final ObjectMapper objectMapper;
    private final ArrivalService arrivalService;

    @GetMapping("/")
    public String hello(Model model) {
        try {
            //지하철 통계 데이터 가져온 후 JsonArray 로 변환
            String congestionJsonArray = objectMapper.writeValueAsString(congestionService.getCongestionByCode("226"));

            //지하철 주요 역 정보 가져 온후 Entity -> DTO 변환
            Map<String, List<MetaInfoResponseDTO>> stationMap = metaInfoService.getStationList();

            //분당선 리스트
            List<BundangLineEntity> bundangLineList = metaInfoService.getBundangLineList();

            //뷰에 전달
            model.addAttribute("congestions",congestionJsonArray);
            model.addAttribute("metaInfo", stationMap);
            model.addAttribute("bundangLine", bundangLineList.stream().map(MetaInfoResponseDTO::new).collect(Collectors.toList()));
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

    @ResponseBody
    @GetMapping("/congestion/count")
    public List<CongestionAndExitCountResponseDTO> getCongestionAndCount(String code) {

        List<CongestionCountEntity> congestionCountEntities = congestionService.getCongestionAndExitCount(code);
        return congestionCountEntities.stream().map(CongestionAndExitCountResponseDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/bundang-line")
    public void setArrivalTime(ArrivalTimeRequestDTO arrivalTime) {
        arrivalService.setArrivalTime(arrivalTime);
    }

}
