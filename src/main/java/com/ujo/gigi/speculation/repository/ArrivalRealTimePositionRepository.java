package com.ujo.gigi.speculation.repository;

import com.ujo.gigi.entity.ArrivalRealTimePositionEntity;
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

    @Select(" SELECT DISTINCT a.TRAIN_NO FROM" +
            " (SELECT TRAIN_NO  FROM STATION_ARRIVAL_REALTIME_POSITION" +
            " WHERE ARRIVAL_STATION_CODE < #{stationCode}" +
            "   AND ARRIVAL_DATE = DATE_FORMAT(NOW(), '%Y%m%d')" +
            "   AND TIMESTAMPDIFF(SECOND, CREATED_AT,NOW()) < 200" +
            "   AND UP_DN_LINE = 1" +
            " ORDER BY CREATED_AT  DESC" +
            " LIMIT 50) a" +
            " LIMIT 5")
    List<ArrivalRealTimePositionEntity> findExpectedTrainNo(@Param("stationCode") String stationCode);

    @Select(" SELECT TIMESTAMPDIFF(SECOND, CONCAT('1994-10-20 ', DATE_FORMAT(CREATED_AT,'%H:%i:%s')), CONCAT('1994-10-20 ',#{arrivalTime})) AS TIME_DIFFERENT, TRAIN_NO, ARRIVAL_DATE FROM STATION_ARRIVAL_REALTIME_POSITION pos" +
            " WHERE TRAIN_NO = #{trainNo}" +
            "   AND ARRIVAL_STATION_CODE = #{stationCode}" +
            "   AND ARRIVAL_CODE = #{arrivalCode}" +
            "   AND ARRIVAL_DATE < DATE_FORMAT(NOW(), '%Y%m%d')" +
            " ORDER BY ABS(TIME_DIFFERENT)" +
            " LIMIT 1")
    ArrivalRealTimePositionEntity findNearDate(@Param("trainNo") String trainNo, @Param("stationCode") String StationCode, @Param("arrivalCode") int arrivalCode,@Param("arrivalTime") String arrivalTime);

    @Select(" SELECT CREATED_AT FROM STATION_ARRIVAL_REALTIME_POSITION" +
            " WHERE TRAIN_NO = #{trainNo}" +
            "   AND ARRIVAL_DATE  = #{arrivalDate}" +
            "   AND ARRIVAL_CODE = #{arrivalCode}" +
            "   AND ARRIVAL_STATION_CODE = #{stationCode}" +
            " LIMIT 1")
    ArrivalRealTimePositionEntity findNextStation(@Param("trainNo") String trainNo, @Param("arrivalDate") String arrivalDate, @Param("arrivalCode") int arrivalCode, @Param("stationCode") String stationCode);

    @Select(" SELECT * FROM STATION_ARRIVAL_REALTIME_POSITION" +
            " ORDER BY CREATED_AT")
    List<ArrivalRealTimePositionEntity> findAll();
}
