package org.example.mhsd.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CarrierCodeMapper {
    @Select("SELECT carrier FROM carrier_code WHERE code = #{code}")
    String getCarrierByCode(@Param("code") String code);
}
