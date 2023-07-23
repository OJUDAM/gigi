package com.ujo.test.gigi.repository;

import com.ujo.test.gigi.dto.response.MetaInfoResponseDTO;
import com.ujo.test.gigi.entity.BaseStationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MetaInfoRepositoryTest {

    @Autowired
    private MetaInfoRepository metaInfoRepository;

    @Test
    void findByPriority() {
        List<BaseStationEntity> metaInfoList = metaInfoRepository.findByPriority(1);

        List<MetaInfoResponseDTO> dtoList =  metaInfoList.stream().map(MetaInfoResponseDTO::new).collect(Collectors.toList());
        for (MetaInfoResponseDTO dto : dtoList) {
            System.out.println(dto);
        }

        Assertions.assertEquals(2, dtoList.size());

    }
}