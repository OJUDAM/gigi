package com.ujo.test.gigi.service;

import com.ujo.test.gigi.dto.response.MetaInfoResponseDTO;
import com.ujo.test.gigi.entity.BaseStationEntity;
import com.ujo.test.gigi.repository.MetaInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetaInfoService {

    private final MetaInfoRepository metaInfoRepository;

    public List<BaseStationEntity> getPrimaryStation(){
        return metaInfoRepository.findByPriority(1);
    }
}
