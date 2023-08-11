package com.ujo.gigi.speculation.repository;

import com.ujo.gigi.common.utils.DateUtils;
import com.ujo.gigi.entity.ArrivalRealTimePositionEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArrivalRealTimePositionRepositoryTest {

    @Autowired
    private ArrivalRealTimePositionRepository arrivalRealTimePositionRepository;

    @Test
    void findNearDate() {
        ArrivalRealTimePositionEntity arrivalRealTimePosition = arrivalRealTimePositionRepository.findNearDate("6409", "K237", 0,"19:27:49");
        ArrivalRealTimePositionEntity arrivalNextStationTime = arrivalRealTimePositionRepository.findNextStation("6409", arrivalRealTimePosition.getArrivalDate(), 0, "K237");

        String presentTime = "192749";
        long time = DateUtils.parseTime(arrivalNextStationTime.getArrivalDate());

        System.out.println("SEX");
        System.out.println(time);
    }
}