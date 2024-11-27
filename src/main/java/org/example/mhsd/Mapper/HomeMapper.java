package org.example.mhsd.Mapper;

import org.example.mhsd.Pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeMapper {
    @Select("SELECT COUNT(*) FROM decoded_data WHERE category = #{category}")
    int countDataByCategory(@Param("category") String category);

    @Select("SELECT * FROM decoded_data WHERE category = '震情'")
    List<DecodedData> getEarthquakeData();

    @Select("SELECT * FROM decoded_data WHERE category = '房屋破坏信息'")
    List<DecodedData> getBuildingDamageData();

    @Select("SELECT * FROM decoded_data WHERE category = '次生灾害'")
    List<DecodedData> getSecondaryData();

    @Select("SELECT * FROM decoded_data WHERE category = '生命线工程灾情'")
    List<DecodedData> getLifelineData();

    @Select("SELECT * FROM decoded_data WHERE category = '人员伤亡及失踪'")
    List<DecodedData> getDeathData();

}
