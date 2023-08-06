package com.ujo.test.speculation.service;

import com.ujo.test.entity.ArrivalRealTimeEntity;
import com.ujo.test.gigi.dto.response.CongestionResponseDTO;
import com.ujo.test.speculation.dto.response.ArrivalRealTimeResponseDTO;
import com.ujo.test.speculation.repository.ArrivalRealTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RealTimeService {

    private final ArrivalRealTimeRepository arrivalRealTimeRepository;

    public List<ArrivalRealTimeResponseDTO> getArrivalTimeList() {
        List<ArrivalRealTimeEntity> arrivalRealTimeEntities = arrivalRealTimeRepository.findAll();

        return arrivalRealTimeEntities.stream().map(ArrivalRealTimeResponseDTO::new).collect(Collectors.toList());
    }
}
