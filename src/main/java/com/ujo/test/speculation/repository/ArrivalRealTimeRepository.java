package com.ujo.test.speculation.repository;

import com.ujo.test.entity.ArrivalRealTimeEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArrivalRealTimeRepository {

    @Insert(" INSERT INTO STATION_ARRIVAL_REALTIME" +
            " ( TRAIN_NO, TRAIN_NAME, ARRIVAL_STATION_CODE" +
            " , ARRIVAL_MESSAGE, TARGET_STATION_CODE, ARRVIAL_CODE" +
            " , DIRECT_AT, UP_DN_LINE, CREATED_AT )" +
            " VALUES" +
            " ( #{trainNo}, #{trainName}, #{arrivalStationCode}" +
            " , #{arrivalMessage}, #{targetStationCode}, #{arrivalCode}" +
            " , #{directAt}, #{upDnLine}, #{createdAt} )")
    int save(ArrivalRealTimeEntity arrivalRealTimeEntity);
}
