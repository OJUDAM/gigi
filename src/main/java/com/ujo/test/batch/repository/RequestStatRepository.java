package com.ujo.test.batch.repository;

import com.ujo.test.batch.entity.RequestStatEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RequestStatRepository {

    @Insert(" INSERT INTO REQUEST_STAT(STATION_CODE, REQUEST_HOUR) " +
            " VALUES(#{stationCode}, #{requestHour})" +
            " ON DUPLICATE KEY UPDATE UPDATED_AT = NOW()")
    int save(RequestStatEntity requestStat);

    @Select("SELECT * FROM REQUEST_STAT")
    List<RequestStatEntity> findAll();
}
