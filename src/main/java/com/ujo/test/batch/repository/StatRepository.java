package com.ujo.test.batch.repository;

import com.ujo.test.batch.entity.StatEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StatRepository {

    @Insert(" INSERT INTO STAT( STATION_CODE, DAY, HOUR" +
            " ,CONGESTION_MIN_00, CONGESTION_MIN_10, CONGESTION_MIN_20 " +
            " ,CONGESTION_MIN_30, CONGESTION_MIN_40, CONGESTION_MIN_50" +
            " ,START_DATE, END_DATE )" +
            " VALUES( #{stationCode}, #{day}, #{hour}" +
            " ,#{congestionMin00}, #{congestionMin10}, #{congestionMin20}" +
            " ,#{congestionMin30}, #{congestionMin40}, #{congestionMin50}" +
            " ,#{startDate}, #{endDate} )" +
            " ON DUPLICATE KEY UPDATE UPDATED_AT = NOW()")
    int save(StatEntity stat);


    @Select("SELECT * FROM STAT WHERE STATION_CODE = #{code}")
    StatEntity findAllByCode(@Param("code") String code);
}
