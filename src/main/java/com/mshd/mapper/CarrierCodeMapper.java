package com.mshd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CarrierCodeMapper {
    @Select("SELECT carrier FROM carrier_code WHERE code = #{code}")
    String getCarrierByCode(@Param("code") String code);
}
