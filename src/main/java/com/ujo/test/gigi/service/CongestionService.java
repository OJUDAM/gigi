package com.ujo.test.gigi.service;

import com.ujo.test.gigi.dto.response.CongestionAndExitCountResponseDTO;
import com.ujo.test.gigi.dto.response.MetaInfoResponseDTO;
import com.ujo.test.gigi.entity.CongestionCountEntity;
import com.ujo.test.gigi.entity.StatAndStationEntity;
import com.ujo.test.gigi.repository.CongestionCountRepository;
import com.ujo.test.gigi.repository.CongestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CongestionService {

    private final CongestionRepository congestionRepository;
    private final CongestionCountRepository congestionCountRepository;

    public List<StatAndStationEntity> getAllCongestion(){
        return congestionRepository.findAll();
    }

    public List<StatAndStationEntity> getCongestionByCode(String stationCode){
        return congestionRepository.findByCode(stationCode);
    }

    public List<CongestionCountEntity> getCongestionAndExitCount(String stationCode) {
        return congestionCountRepository.findAllByCode(stationCode);
    }

    public Map<String, List<MetaInfoResponseDTO>> getStationList() {
        //호선 마다 나누기 위해 맵 성성
        Map<String, List<MetaInfoResponseDTO>> stationMap = new HashMap<>(10);


        List<CongestionCountEntity> stationList = congestionCountRepository.findStation();

        for (CongestionCountEntity station : stationList) {
            MetaInfoResponseDTO metaInfoResponseDTO = new MetaInfoResponseDTO(station);

            List<MetaInfoResponseDTO> metaInfoList = new ArrayList<>();
            if (stationMap.containsKey(metaInfoResponseDTO.getSubwayLine())) {
                metaInfoList = stationMap.get(metaInfoResponseDTO.getSubwayLine());
                metaInfoList.add(metaInfoResponseDTO);

                stationMap.put(metaInfoResponseDTO.getSubwayLine(), metaInfoList);

                continue;
            }

            metaInfoList.add(metaInfoResponseDTO);
            stationMap.put(metaInfoResponseDTO.getSubwayLine(), metaInfoList);

        }
        return stationMap;
    }
}
