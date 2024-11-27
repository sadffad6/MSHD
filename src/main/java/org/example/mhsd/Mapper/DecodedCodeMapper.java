package org.example.mhsd.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DecodedCodeMapper {
    @Insert("INSERT INTO decoded_data (code,location, date,source,carrier,category,subcategory,indicator) VALUES (#{Code},#{location}, #{date},#{source},#{carrier},#{category},#{subcategory},#{indicator})")
    void insertDecodedCode(@Param("Code") String code,
                           @Param("location") String location,
                           @Param("date") String date,
                           @Param("source") String source,
                           @Param("carrier") String carrier,
                           @Param("category") String category,
                           @Param("subcategory") String subcategory,
                           @Param("indicator") String indicator);
}
