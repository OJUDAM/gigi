package com.ujo.test.gigi.repository;

import com.ujo.test.gigi.entity.StatAndStationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CongestionRepository {
    @Select(" SELECT stat.*, station.STATION_NAME, station.SUBWAY_LINE" +
            " FROM STATION_STAT stat" +
            " INNER JOIN STATION station ON stat.STATION_CODE = station.STATION_CODE" +
            " WHERE stat.UP_DN_LINE = 1")
    List<StatAndStationEntity> findAll();

    @Select(" SELECT stat.*, station.STATION_NAME, station.SUBWAY_LINE" +
            " FROM STATION_STAT stat" +
            " INNER JOIN STATION station ON stat.STATION_CODE = station.STATION_CODE" +
            " WHERE stat.UP_DN_LINE = 1" +
            " AND stat.STATION_CODE = #{code}")
    List<StatAndStationEntity> findByCode(@Param("code") String code);
}
