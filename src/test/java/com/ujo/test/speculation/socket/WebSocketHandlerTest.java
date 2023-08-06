package com.ujo.test.speculation.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujo.test.entity.ArrivalRealTimeEntity;
import com.ujo.test.speculation.dto.response.ArrivalRealTimeResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WebSocketHandlerTest {
    @Test
    void listToJsonArray() throws JsonProcessingException {
        List<ArrivalRealTimeResponseDTO> arrivalList = new ArrayList<>();
        ArrivalRealTimeEntity arrivalEntity = new ArrivalRealTimeEntity();
        arrivalEntity.setTrainNo("6450");
        arrivalEntity.setTrainName("고색");
        arrivalEntity.setArrivalMessage("[4]번째 전역 (기흥)");
        ArrivalRealTimeEntity arrivalEntity2 = new ArrivalRealTimeEntity();
        arrivalEntity2.setTrainNo("6171");
        arrivalEntity2.setTrainName("고색");
        arrivalEntity2.setArrivalMessage("망포 진입");

        ArrivalRealTimeResponseDTO arrivalDTO = new ArrivalRealTimeResponseDTO(arrivalEntity);
        ArrivalRealTimeResponseDTO arrivalDTO2 = new ArrivalRealTimeResponseDTO(arrivalEntity2);

        arrivalList.add(arrivalDTO);
        arrivalList.add(arrivalDTO2);

        System.out.println( new ObjectMapper().writeValueAsString(arrivalList));
    }

}