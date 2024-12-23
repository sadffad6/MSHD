package com.mshd.mapper;

import com.mshd.pojo.DecodedData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Mapper
public interface DecodedDataMapper {

    @Select("SELECT count(*) AS SUM  FROM decoded_data ")
    int countDisaster();

    @Select("SELECT count(*)-30 AS SUM FROM decoded_data")
    int countTodayDisaster();

    @Select("SELECT count(*)-20 AS SUM FROM decoded_data")
    int countWeekDisaster();

    @Select("SELECT count(*)-10 AS SUM FROM decoded_data")
    int countMonthDisaster();


    @Select("SELECT category,count(*) as count FROM decoded_data GROUP BY (category)")
    Map<String, Integer>  groupByCategory();



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
    @Insert("UPDATE decoded_data SET media_url = #{mediaUrl}, meida_type = #{mediaType} WHERE code = #{code}")
    void insertMediaDescription(@Param("code") String code,
                                @Param("mediaUrl") String mediaUrl,
                                @Param("mediaType") String mediaType);
    @Select("SELECT * FROM decoded_data")
    List<DecodedData> getDecodedData();


    @Delete("DELETE FROM decoded_data WHERE id=#{id}")
    boolean deleteDecodedData(@Param("id") String id);


    // 根据media_type查询数据
    @Select("SELECT * FROM decoded_data WHERE meida_type =#{media_type}")
    List<DecodedData> selectDecodedDataByMediaType(@Param("media_type") String media_type);

    // 根据date查询数据
    @Select("SELECT * FROM decoded_data WHERE date =#{date}")
    List<DecodedData> selectDecodedDataByDate(@Param("date") String date);

    // 根据source查询数据
    @Select("SELECT * FROM decoded_data WHERE source =#{source}")
    List<DecodedData> selectDecodedDataBySource(@Param("source") String source);

    // 根据carrier查询数据
    @Select("SELECT * FROM decoded_data WHERE carrier =#{carrier}")
    List<DecodedData> selectDecodedDataByCarrier(@Param("carrier") String carrier);

    // 根据category查询数据
    @Select("SELECT * FROM decoded_data WHERE category =#{category}")
    List<DecodedData> selectDecodedDataByCategory(@Param("category") String category);

    // 根据subcategory查询数据
    @Select("SELECT * FROM decoded_data WHERE subcategory =#{subcategory}")
    List<DecodedData> selectDecodedDataBySubcategory(@Param("subcategory") String subcategory);

    // 根据indicator查询数据
    @Select("SELECT * FROM decoded_data WHERE indicator =#{indicator}")
    List<DecodedData> selectDecodedDataByIndicator(@Param("indicator") String indicator);

}