package com.mshd.service;

import com.mshd.mapper.*;
import com.mshd.pojo.DecodedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DecodeDataService {
    private final DecodedDataMapper decodedCodeMapper;
    private final RegionCodeMapper regionCodeMapper;

    private final SourceCodeMapper sourceCodeMapper;

    private final CarrierCodeMapper carrierCodeMapper;

    private final DisasterCodeMapper disasterCodeMapper;

    @Autowired
    public DecodeDataService(DecodedDataMapper decodedCodeMapper, RegionCodeMapper regionCodeMapper, SourceCodeMapper sourceCodeMapper, CarrierCodeMapper carrierCodeMapper, DisasterCodeMapper disasterCodeMapper) {
        this.decodedCodeMapper = decodedCodeMapper;
        this.regionCodeMapper = regionCodeMapper;
        this.sourceCodeMapper = sourceCodeMapper;
        this.carrierCodeMapper = carrierCodeMapper;
        this.disasterCodeMapper = disasterCodeMapper;
    }

    public String decodeAndSave(String code, String description, String mediaUrl, String mediaType) {
        String message = "";
        String Code = code;
        Code = Code.substring(0,Code.length()-1);

        String location = decodeLocation(code.substring(0, 12));
        String date = decodeDate(code.substring(12,26));
        String source = decodeSource(code.substring(26,29));
        String carrier = decodeCarrier(code.substring(29,30));
        String disaster=getDisasterFromCode(code.substring(30,36));
        String category=disaster.split("-")[0];
        String subcategory=disaster.split("-")[1];
        String indicator=disaster.split("-")[2];

        decodedCodeMapper.insertDecodedCode(Code,location, date, source, carrier,category,subcategory,indicator);

        if (description != null && !description.isEmpty()) {
            decodedCodeMapper.insertDescriptionText(Code, description);
            message = location + date + source + carrier + disaster + ": " + description;
        } else if (mediaUrl != null && !mediaUrl.isEmpty() && mediaType != null && !mediaType.isEmpty()) {
            decodedCodeMapper.insertMediaDescription(Code, mediaUrl, mediaType);
            message = location + date + source +carrier + disaster + ": " + mediaType;
        }

        return message;
    }

    private String decodeCarrier(String code){return carrierCodeMapper.getCarrierByCode(code);}

    private String decodeSource(String code) {return sourceCodeMapper.getSourceByCode(code);}

    private String decodeLocation(String code) {
        return regionCodeMapper.getRegionByCode(code);
    }

    private String getDisasterFromCode(String code) {
        String disaster = disasterCodeMapper.getDisasterByCode(code);
        return disaster != null ? disaster : "";
    }

    private String decodeDate(String code) {
        String year = code.substring(0, 4);
        String month = code.substring(4, 6);
        String day = code.substring(6, 8);
        String hour = code.substring(8, 10);
        String minute = code.substring(10, 12);
        String second = code.substring(12, 14);

        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    public List<DecodedData> getDecodeddata() {
        return decodedCodeMapper.getDecodedData();
    }

    public List<Map<String, Object>> getAllDecodedData() {
        return decodedCodeMapper.getAllDecodedData();
    }

    public List<DecodedData> getDecodedDataByMediaType(String media_type) {
        try {
            return decodedCodeMapper.selectDecodedDataByMediaType(media_type);
        } catch (Exception e) {
            // 这里可以记录日志，方便后续排查问题，比如使用日志框架记录异常信息
            e.printStackTrace();
            // 根据业务需求返回合适的默认值，比如返回空列表
            return List.of();
        }
    }

    public boolean deleteDecodedCode(String id) {
        return decodedCodeMapper.deleteDecodedData(id);
    }

    // 根据date查询数据
    public List<DecodedData> getDecodedDataByDate(String date) {
        try {
            return decodedCodeMapper.selectDecodedDataByDate(date);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // 根据source查询数据
    public List<DecodedData> getDecodedDataBySource(String source) {
        try {
            return decodedCodeMapper.selectDecodedDataBySource(source);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // 根据carrier查询数据
    public List<DecodedData> getDecodedDataByCarrier(String carrier) {
        try {
            return decodedCodeMapper.selectDecodedDataByCarrier(carrier);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // 根据category查询数据
    public List<DecodedData> getDecodedDataByCategory(String category) {
        try {
            return decodedCodeMapper.selectDecodedDataByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // 根据subcategory查询数据
    public List<DecodedData> getDecodedDataBySubcategory(String subcategory) {
        try {
            return decodedCodeMapper.selectDecodedDataBySubcategory(subcategory);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // 根据indicator查询数据
    public List<DecodedData> getDecodedDataByIndicator(String indicator) {
        try {
            return decodedCodeMapper.selectDecodedDataByIndicator(indicator);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}