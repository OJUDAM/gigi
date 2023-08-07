package com.ujo.test.speculation.repository;

import com.ujo.test.entity.ArrivalRealTimeEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArrivalRealTimeRepository {

    @Insert(" INSERT INTO STATION_ARRIVAL_REALTIME" +
            " ( TRAIN_NO, TRAIN_NAME, ARRIVAL_STATION_CODE" +
            " , ARRIVAL_MESSAGE, TARGET_STATION_CODE, ARRIVAL_CODE" +
            " , DIRECT_AT, UP_DN_LINE, ARRIVAL_DATE,CREATED_AT )" +
            " VALUES" +
            " ( #{trainNo}, #{trainName}, #{arrivalStationCode}" +
            " , #{arrivalMessage}, #{targetStationCode}, #{arrivalCode}" +
            " , #{directAt}, #{upDnLine}, #{arrivalDate}, #{createdAt} )" +
            " ON DUPLICATE KEY UPDATE UPDATED_AT = NOW(), ARRIVAL_MESSAGE = #{arrivalMessage}" +
            " , ARRIVAL_STATION_CODE = #{arrivalStationCode}, ARRIVAL_CODE=#{arrivalCode}, CREATED_AT = #{createdAt}")
    int save(ArrivalRealTimeEntity arrivalRealTimeEntity);

    @Select("   SELECT * FROM STATION_ARRIVAL_REALTIME" +
            " WHERE ARRIVAL_DATE = DATE_FORMAT(NOW(), '%Y%m%d')" +
            "   AND (ARRIVAL_CODE = 99 OR ARRIVAL_CODE = 5 OR ARRIVAL_CODE = 4" +
            "    OR (TIMEDIFF(UPDATED_AT, CREATED_AT) < 45 AND ARRIVAL_CODE = 1) OR (TIMEDIFF(UPDATED_AT, CREATED_AT) < 80 AND ARRIVAL_CODE = 0))" +
            " ORDER BY TRAIN_NO" +
            " LIMIT 5")
    List<ArrivalRealTimeEntity> findToDay();

    @Select(" SELECT * FROM STATION_ARRIVAL_REALTIME" +
            " ORDER BY CREATED_AT")
    List<ArrivalRealTimeEntity> findAll();
}
