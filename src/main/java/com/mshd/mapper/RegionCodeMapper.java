package com.mshd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegionCodeMapper {
    @Select("SELECT region FROM region_code WHERE code = #{code}")
    String getRegionByCode(@Param("code") String code);
}