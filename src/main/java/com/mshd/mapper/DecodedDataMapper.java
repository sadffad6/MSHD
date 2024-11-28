package com.mshd.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DecodedDataMapper {
    @Insert("INSERT INTO decoded_data (code,location, date,source,carrier,category,subcategory,indicator) VALUES (#{Code},#{location}, #{date},#{source},#{carrier},#{category},#{subcategory},#{indicator})")
    void insertDecodedCode(@Param("Code") String code,
                           @Param("location") String location,
                           @Param("date") String date,
                           @Param("source") String source,
                           @Param("carrier") String carrier,
                           @Param("category") String category,
                           @Param("subcategory") String subcategory,
                           @Param("indicator") String indicator);
    // 插入文本描述
    @Insert("UPDATE decoded_data SET description_text = #{descriptionText} WHERE code = #{code}")
    void insertDescriptionText(@Param("code") String code,
                               @Param("descriptionText") String descriptionText);

    // 插入媒体文件描述
    @Insert("UPDATE decoded_data SET media_url = #{mediaUrl}, media_type = #{mediaType} WHERE code = #{code}")
    void insertMediaDescription(@Param("code") String code,
                                @Param("mediaUrl") String mediaUrl,
                                @Param("mediaType") String mediaType);
}