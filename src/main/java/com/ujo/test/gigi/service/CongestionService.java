package com.ujo.test.gigi.service;

import com.ujo.test.gigi.entity.StatAndStationEntity;
import com.ujo.test.gigi.repository.CongestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CongestionService {

    private final CongestionRepository congestionRepository;

    public List<StatAndStationEntity> getAllCongestion(){
        return congestionRepository.findAll();
    }
}
