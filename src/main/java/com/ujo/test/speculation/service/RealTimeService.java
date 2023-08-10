package com.ujo.test.speculation.service;

import com.ujo.test.entity.ArrivalRealTimeEntity;
import com.ujo.test.entity.ArrivalRealTimePositionEntity;
import com.ujo.test.speculation.dto.response.ArrivalRealTimePositionResponseDTO;
import com.ujo.test.speculation.dto.response.ArrivalRealTimeResponseDTO;
import com.ujo.test.speculation.repository.ArrivalRealTimePositionRepository;
import com.ujo.test.speculation.repository.ArrivalRealTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RealTimeService {

    private final ArrivalRealTimeRepository arrivalRealTimeRepository;
    private final ArrivalRealTimePositionRepository arrivalRealTimePositionRepository;

    public List<ArrivalRealTimeResponseDTO> getArrivalTimeList() {
        List<ArrivalRealTimeEntity> arrivalRealTimeEntities = arrivalRealTimeRepository.findToDay();

        return arrivalRealTimeEntities.stream().map(ArrivalRealTimeResponseDTO::new).collect(Collectors.toList());
    }

    public List<ArrivalRealTimePositionResponseDTO> getArrivalTimePositionList() {
        //화연에 표시할 역기준 도착 정보에 따른 열차 위치 정보담을 리스트 생성
        List<ArrivalRealTimePositionEntity> arrivalRealTimeEntities = new ArrayList<>();

        //화면에 표시할 역 기준 도착 정보 조회
        List<ArrivalRealTimeResponseDTO> realtimeList = getArrivalTimeList();

        for (ArrivalRealTimeResponseDTO dto : realtimeList) {
            //열차 위지 정보 세팅
            arrivalRealTimeEntities.add(arrivalRealTimePositionRepository.findByTrainNoToday(dto.getTrainNo()));
        }

        return arrivalRealTimeEntities.stream().map(ArrivalRealTimePositionResponseDTO::new).collect(Collectors.toList());
    }


}
