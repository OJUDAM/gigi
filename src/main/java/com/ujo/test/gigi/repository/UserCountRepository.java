package com.ujo.test.gigi.repository;

import com.ujo.test.gigi.entity.ExitAndStationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCountRepository {

    @Select(" SELECT b.STATION_NAME, b.SUBWAY_LINE, a.*" +
            " FROM STATION_EXIT_USER_COUNT a" +
            " INNER JOIN STATION b ON b.STATION_CODE = a.STATION_CODE" +
            " WHERE a.STATION_CODE =  #{code}")
    List<ExitAndStationEntity> findAllByCode(@Param("code") String code);

    @Select(" SELECT STATION_CODE, USER_COUNT_17, USER_COUNT_18, USER_COUNT_19" +
            " FROM STATION_EXIT_USER_COUNT" +
            " WHERE STATION_CODE =  #{code}")
    List<ExitAndStationEntity> findUserCountByCode(@Param("code") String code);

}
