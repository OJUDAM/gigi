package com.ujo.test.gigi.repository;

import com.ujo.test.gigi.entity.CongestionCountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CongestionCountRepository {
    @Select(" SELECT * FROM CONGESTION_COUNT_VIEW" +
            " WHERE STATION_CODE = #{code}")
    List<CongestionCountEntity> findAllByCode(@Param("code") String code);

    @Select(" SELECT STATION_CODE, STATION_NAME, SUBWAY_LINE" +
            " FROM CONGESTION_COUNT_VIEW" +
            " GROUP BY STATION_CODE")
    List<CongestionCountEntity> findStation();
}
