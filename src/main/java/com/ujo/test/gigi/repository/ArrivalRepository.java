package com.ujo.test.gigi.repository;

import com.ujo.test.entity.ArrivalEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArrivalRepository {

    @Insert(" INSERT INTO STATION_ARRIVAL (STATION_CODE, ARRIVAL_YEAR, ARRIVAL_MONTH" +
            " , ARRIVAL_DAY, ARRIVAL_TIME, MESSAGE)" +
            " VALUES(#{stationCode}, #{arrivalYear}, #{arrivalMonth}" +
            " , #{arrivalDay}, #{arrivalTime}, #{message})")
    int save(ArrivalEntity arrivalEntity);


}
