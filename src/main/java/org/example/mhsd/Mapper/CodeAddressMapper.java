package org.example.mhsd.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CodeAddressMapper {
    @Select("SELECT address FROM code_address WHERE code = #{code}")
    String getLocationByCode(@Param("code") String code);
}