package com.ujo.gigi.gigi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujo.gigi.gigi.dto.response.CongestionResponseDTO;
import com.ujo.gigi.entity.StatAndStationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
class CongestionRepositoryTest {

    @Autowired
    private CongestionRepository congestionRepository;

    @Test
    void findAll() {
        List<StatAndStationEntity> list = congestionRepository.findAll();

        for (StatAndStationEntity entity : list) {
            System.out.println(entity.toString());
        }

        ObjectMapper mapper = new ObjectMapper();

        List<CongestionResponseDTO> congestionResponseDTOS = list.stream()
                .map(data -> new CongestionResponseDTO(data))
                .collect(Collectors.toList());

        for (CongestionResponseDTO dto : congestionResponseDTOS) {
            System.out.println(dto.toString());
        }

        Assertions.assertNotNull(list);

    }
}