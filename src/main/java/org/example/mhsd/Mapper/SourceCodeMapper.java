package org.example.mhsd.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SourceCodeMapper {
    @Select("SELECT source FROM source_code WHERE code = #{code}")
    String getSourceByCode(@Param("code") String code);
}
