package org.example.mhsd.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DisasterCodeMapper {
    @Select("SELECT disaster FROM disaster_code WHERE code = #{code}")
    String getDisasterByCode(@Param("code") String code);


}
