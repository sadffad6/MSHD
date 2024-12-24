package com.mshd.service;

import com.mshd.mapper.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisualizationService {
    private final DecodedDataMapper decodedCodeMapper;
    private final RegionCodeMapper regionCodeMapper;

    private final SourceCodeMapper sourceCodeMapper;

    private final CarrierCodeMapper carrierCodeMapper;

    private final DisasterCodeMapper disasterCodeMapper;

    @Autowired
    public VisualizationService(DecodedDataMapper decodedCodeMapper, RegionCodeMapper regionCodeMapper, SourceCodeMapper sourceCodeMapper, CarrierCodeMapper carrierCodeMapper, DisasterCodeMapper disasterCodeMapper) {
        this.decodedCodeMapper = decodedCodeMapper;
        this.regionCodeMapper = regionCodeMapper;
        this.sourceCodeMapper = sourceCodeMapper;
        this.carrierCodeMapper = carrierCodeMapper;
        this.disasterCodeMapper = disasterCodeMapper;
    }


    public Map<String, Object> countDisaster() {
        Map<String, Object> resultMap = new HashMap<>();
        // 先获取各个灾害相关数据的统计值
        int disasterCounts = decodedCodeMapper.countDisaster();
        int todayDisasterCount = decodedCodeMapper.countTodayDisaster();
        int weekDisasterCount = decodedCodeMapper.countWeekDisaster();
        int monthDisasterCount = decodedCodeMapper.countMonthDisaster();
        List<Map<String, Integer>> disasterMap=decodedCodeMapper.groupByCategory();

        List<Map<String, Integer>> locationDisasterMap=decodedCodeMapper.groupByLocation();




        // 设置disaster_counts的值
        resultMap.put("disaster_counts", disasterCounts);

        // 创建new_data对应的内层Map，并设置相应的值
        Map<String, Integer> newDataMap = new HashMap<>();
        newDataMap.put("today", todayDisasterCount);
        newDataMap.put("week", weekDisasterCount);
        newDataMap.put("month", monthDisasterCount);

        // 将new_data内层Map放入外层resultMap中
        resultMap.put("new_data", newDataMap);
        resultMap.put("type_distribution",disasterMap);
        resultMap.put("location_distribution",locationDisasterMap);


        return resultMap;
    }


}
