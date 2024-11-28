package com.mshd.controller;

import com.mshd.service.DecodeDataService;
import com.mshd.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class DecodedDataController {
    private final DecodeDataService decodeDataService;

    public DecodedDataController(DecodeDataService decodeDataService) {
        this.decodeDataService = decodeDataService;
    }

    @PostMapping("/decode/text")
    public R decodeAndSaveText(@RequestParam("code") String code,
                               @RequestParam("description") String description) {
        String decodedData = decodeDataService.decodeAndSave(code, description, null, null);
        return R.ok("Decoded data with text: " + decodedData);
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
}

