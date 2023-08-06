package com.ujo.test.gigi.repository;

import com.ujo.test.entity.BundangLineEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BundangLineRepository {

    @Select("SELECT * FROM STATION_BUNDANG_LINE")
    List<BundangLineEntity> findAll();

}
