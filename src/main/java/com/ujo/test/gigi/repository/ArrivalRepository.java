package com.ujo.test.gigi.repository;

import com.ujo.test.gigi.entity.ArrivalEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArrivalRepository {

    @Insert(" INSERT INTO STATION_ARRIVAL (STATION_CODE, ARRIVAL_YEAR, ARRIVAL_MONTH" +
            " , ARRIVAL_DAY, ARRIVAL_TIME)" +
            " VALUES(#{stationCode}, #{arrivalYear}, #{arrivalMonth}" +
            " , #{arrivalDay}, #{arrivalTime})")
    int save(ArrivalEntity arrivalEntity);


}
