package com.mshd.controller;

import com.mshd.pojo.DecodedData;
import com.mshd.service.DecodeDataService;
import com.mshd.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.mshd.utils.jwt.JwtToken;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class DecodedDataController {

    private final DecodeDataService decodeDataService;

    public DecodedDataController(DecodeDataService decodeDataService) {
        this.decodeDataService = decodeDataService;
    }

    @JwtToken
    @PostMapping("/decode/text")
    public R decodeAndSaveText(@RequestParam("code") String code,
                               @RequestParam("description") String description) {
        String decodedData = decodeDataService.decodeAndSave(code, description, null, null);
        return R.ok("Decoded data with text: " + decodedData);
    }

    @JwtToken
    @GetMapping("/decode/data")
    public R decodeAndSaveData(){
        return R.ok(decodeDataService.getDecodeddata());
    }

    // 接收编码和媒体文件（图片、音频、视频）的接口
    @PostMapping("/decode/media")
    public R decodeAndSaveMedia(@RequestParam("code") String code,
                                @RequestParam("file") MultipartFile file) {

        String mediaUrl = uploadMediaFile(file);
        String mediaType = determineMediaType(file);

        String decodedData = decodeDataService.decodeAndSave(code, null, mediaUrl, mediaType);
        return R.ok("Decoded data with media: " + decodedData);
    }

    // 上传文件（此处简化为直接返回 URL)
    private String uploadMediaFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return "/media/" + filename;
    }

    // 判断文件类型
    private String determineMediaType(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType != null) {
            if (contentType.startsWith("image")) {
                return "image";
            } else if (contentType.startsWith("audio")) {
                return "audio";
            } else if (contentType.startsWith("video")) {
                return "video";
            }
        }
        return "unknown";
    }

    @JwtToken
    @PostMapping("/decode/delete")
    private R deleteDecodedData(@RequestParam("id") String id) {
        boolean yn=decodeDataService.deleteDecodedCode(id);
        if (yn) {
            return R.ok(yn);
        }else {
            return R.failure("失败");
        }}
    @PostMapping("/query")
    public R queryData(
            @RequestParam(name = "media_type", required = false) String media_type,
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "source", required = false) String source,
            @RequestParam(name = "carrier", required = false) String carrier,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "subcategory", required = false) String subcategory,
            @RequestParam(name = "indicator", required = false) String indicator) {

        List<DecodedData> resultList = new ArrayList<>();
        boolean hasQuery = false;

        try {
            // 根据media_type查询数据
            if (media_type!= null &&!media_type.isEmpty()) {
                List<DecodedData> mediaTypeData = decodeDataService.getDecodedDataByMediaType(media_type);
                resultList.addAll(mediaTypeData);
                hasQuery = true;
            }

            // 根据date查询数据
            if (date!= null &&!date.isEmpty()) {
                List<DecodedData> dateData = decodeDataService.getDecodedDataByDate(date);
                resultList.addAll(dateData);
                hasQuery = true;
            }

            // 根据source查询数据
            if (source!= null &&!source.isEmpty()) {
                List<DecodedData> sourceData = decodeDataService.getDecodedDataBySource(source);
                resultList.addAll(sourceData);
                hasQuery = true;
            }

            // 根据carrier查询数据
            if (carrier!= null &&!carrier.isEmpty()) {
                List<DecodedData> carrierData = decodeDataService.getDecodedDataByCarrier(carrier);
                resultList.addAll(carrierData);
                hasQuery = true;
            }

            // 根据category查询数据
            if (category!= null &&!category.isEmpty()) {
                List<DecodedData> categoryData = decodeDataService.getDecodedDataByCategory(category);
                resultList.addAll(categoryData);
                hasQuery = true;
            }

            // 根据subcategory查询数据
            if (subcategory!= null &&!subcategory.isEmpty()) {
                List<DecodedData> subcategoryData = decodeDataService.getDecodedDataBySubcategory(subcategory);
                resultList.addAll(subcategoryData);
                hasQuery = true;
            }

            // 根据indicator查询数据
            if (indicator!= null &&!indicator.isEmpty()) {
                List<DecodedData> indicatorData = decodeDataService.getDecodedDataByIndicator(indicator);
                resultList.addAll(indicatorData);
                hasQuery = true;
            }

            if (hasQuery) {
                return R.ok(resultList);
            } else {

                return R.failure("缺少数据");
            }
        } catch (Exception e) {
            e.printStackTrace();

            return R.failure("查询结果异常");
        }
    }
}
