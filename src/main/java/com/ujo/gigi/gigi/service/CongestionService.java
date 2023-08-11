package com.ujo.gigi.gigi.service;

import com.ujo.gigi.entity.CongestionCountEntity;
import com.ujo.gigi.entity.StatAndStationEntity;
import com.ujo.gigi.gigi.repository.CongestionCountRepository;
import com.ujo.gigi.gigi.repository.CongestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
