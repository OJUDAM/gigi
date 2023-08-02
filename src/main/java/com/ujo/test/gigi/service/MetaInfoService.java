package com.ujo.test.gigi.service;

import com.ujo.test.gigi.dto.response.MetaInfoResponseDTO;
import com.ujo.test.gigi.entity.BaseStationEntity;
import com.ujo.test.gigi.entity.BundangLineEntity;
import com.ujo.test.gigi.entity.CongestionCountEntity;
import com.ujo.test.gigi.repository.BundangLineRepository;
import com.ujo.test.gigi.repository.CongestionCountRepository;
import com.ujo.test.gigi.repository.MetaInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetaInfoService {

    private final MetaInfoRepository metaInfoRepository;
    private final CongestionCountRepository congestionCountRepository;
    private final BundangLineRepository bundangLineRepository;


    public List<BaseStationEntity> getPrimaryStation(){
        return metaInfoRepository.findByPriority(1);
    }

    public List<BundangLineEntity> getBundangLineList(){
        return bundangLineRepository.findAll();
    }
    public Map<String, List<MetaInfoResponseDTO>> getStationList() {
        //호선 마다 나누기 위해 맵 성성
        Map<String, List<MetaInfoResponseDTO>> stationMap = new TreeMap<>();

        //주요 역 정보 담음
        stationMap.put("0주요역", metaInfoRepository.findByPriority(1)
                .stream().map(MetaInfoResponseDTO::new)
                .collect(Collectors.toList()));

        //통계 자료 있는 지하철 역 리스트 저장
        List<CongestionCountEntity> stationList = congestionCountRepository.findStation();

        //key: 호선 / value: 호선에 따른 지하철 역 리스트인 map 생성하기 위한 로직
        for (CongestionCountEntity station : stationList) {
            //entity -> dto 변환
            MetaInfoResponseDTO metaInfoResponseDTO = new MetaInfoResponseDTO(station);

            //지하철 역 정보 담을 리스트 생성 및 초기화
            List<MetaInfoResponseDTO> metaInfoList = new ArrayList<>();

            //키가 이미 존재할 경우 value 값에 추가
            if (stationMap.containsKey(metaInfoResponseDTO.getSubwayLine())) {
                metaInfoList = stationMap.get(metaInfoResponseDTO.getSubwayLine());

                metaInfoList.add(metaInfoResponseDTO);
                stationMap.put(metaInfoResponseDTO.getSubwayLine(), metaInfoList);

                continue;
            }

            //리스트에 역 정보 추가 후 map 에 저장
            metaInfoList.add(metaInfoResponseDTO);
            stationMap.put(metaInfoResponseDTO.getSubwayLine(), metaInfoList);

        }

        return stationMap;
    }
}
