package com.ujo.test.speculation.repository;

import com.ujo.test.entity.ArrivalRealTimeEntity;
import com.ujo.test.entity.ArrivalRealTimePositionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArrivalRealTimePositionRepository {

    @Select("  SELECT * FROM STATION_ARRIVAL_REALTIME_POSITION" +
            " WHERE ARRIVAL_DATE = DATE_FORMAT(NOW(), '%Y%m%d')" +
            "   AND TRAIN_NO = #{stationCode}" +
            " ORDER BY CREATED_AT DESC" +
            " LIMIT 1")
    ArrivalRealTimePositionEntity findByTrainNoToday(@Param("stationCode") String stationCode);

    @Select(" SELECT * FROM STATION_ARRIVAL_REALTIME_POSITION" +
            " ORDER BY CREATED_AT")
    List<ArrivalRealTimePositionEntity> findAll();
}
