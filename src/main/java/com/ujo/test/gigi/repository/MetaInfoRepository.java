package com.ujo.test.gigi.repository;

import com.ujo.test.gigi.entity.BaseStationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MetaInfoRepository {

    @Select(" SELECT * FROM STATION" +
            " WHERE priority = #{priority}")
    List<BaseStationEntity> findByPriority(@Param("priority") int priority);

}
