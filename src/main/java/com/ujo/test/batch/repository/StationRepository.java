package com.ujo.test.batch.repository;

import com.ujo.test.batch.entity.StationEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StationRepository {

    @Insert(" INSERT INTO STATION(STATION_CODE, STATION_NAME, SUBWAY_LINE) " +
            " VALUES(#{stationCode}, #{stationName}, #{subwayLine})" +
            " ON DUPLICATE KEY UPDATE UPDATED_AT = NOW()")
    int save(StationEntity station);

    @Select("SELECT * FROM STATION WHERE STATION_CODE = #{code}")
    StationEntity findAllByCode(@Param("code") String code);
}
