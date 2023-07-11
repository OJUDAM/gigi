package com.ujo.test.batch.repository;

import com.ujo.test.batch.entity.StationEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class StationRepositoryTest {

    @Autowired
    private StationRepository stationRepository;

    @Test
    void saveTest(){
        StationEntity station = new StationEntity();
        station.setStationCode("D12");
        station.setStationName("정자역");
        station.setSubwayLine("신분당선");


        int result = stationRepository.save(station);

        int updateResult = stationRepository.save(station);

        System.out.println(result);

        Assert.assertEquals(1 , updateResult);
    }

    @Test
    void findTest(){
        StationEntity station = stationRepository.findAllByCode("D12");

        System.out.println(station.toString());

        Assert.assertEquals("정자역", station.getStationName());
    }

}