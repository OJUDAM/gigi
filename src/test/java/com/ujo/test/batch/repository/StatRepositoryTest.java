package com.ujo.test.batch.repository;

import com.ujo.test.batch.entity.StatEntity;
import com.ujo.test.batch.entity.StatMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class StatRepositoryTest {

    @Autowired
    private StatRepository statRepository;
    @Autowired
    private StatMapper statMapper;
    @Test
    void save() {
        String response = "{\"status\":\n" +
                "{\"code\":\"00\",\"message\":\"success\",\"totalCount\":1},\"contents\":{\"subwayLine\":\"신분당선\",\"stationName\":\"정자역\",\"stationCode\":\"D12\",\"stat\":[{\"startStationCode\":\"D04\",\"startStationName\":\"신사역\",\"endStationCode\":\"D12\",\"endStationName\":\"정자역\",\"prevStationCode\":\"D11\",\"prevStationName\":\"판교역\",\"updnLine\":1,\"directAt\":0,\"data\":[{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"00\",\"congestionTrain\":0},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"10\",\"congestionTrain\":0},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"20\",\"congestionTrain\":0},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"30\",\"congestionTrain\":0},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"40\",\"congestionTrain\":0},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"50\",\"congestionTrain\":0}]},{\"startStationCode\":\"D04\",\"startStationName\":\"신사역\",\"endStationCode\":\"D19\",\"endStationName\":\"광교역\",\"prevStationCode\":\"D11\",\"prevStationName\":\"판교역\",\"updnLine\":1,\"directAt\":0,\"data\":[{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"00\",\"congestionTrain\":104},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"10\",\"congestionTrain\":102},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"20\",\"congestionTrain\":126},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"30\",\"congestionTrain\":140},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"40\",\"congestionTrain\":147},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"50\",\"congestionTrain\":146}]},{\"startStationCode\":\"D19\",\"startStationName\":\"광교역\",\"endStationCode\":\"D04\",\"endStationName\":\"신사역\",\"prevStationCode\":\"D13\",\"prevStationName\":\"미금역\",\"updnLine\":0,\"directAt\":0,\"data\":[{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"00\",\"congestionTrain\":58},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"10\",\"congestionTrain\":67},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"20\",\"congestionTrain\":77},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"30\",\"congestionTrain\":71},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"40\",\"congestionTrain\":78},{\"dow\":\"FRI\",\"hh\":\"17\",\"mm\":\"50\",\"congestionTrain\":62}]}],\"statStartDate\":\"20230329\",\"statEndDate\":\"20230628\"}}";

        StatEntity statEntity = StatEntity.from(statMapper.jsonToMap(response));

        int result = statRepository.save(statEntity);

        Assertions.assertEquals(1, result);

    }
}