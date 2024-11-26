package org.example.mhsd.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.mhsd.Service.DataReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/data")
public class DataReceiverController {

    @Autowired
    private DataReceiverService dataReceiverService;

    // 接收文本数据
    @PostMapping
    public ResponseEntity<Map<String, Object>> receiveTextData(HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            // 将请求转换为MultipartHttpServletRequest类型，以便处理form-data格式
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            // 获取form-data中的文本数据（假设文本数据对应的参数名为textData）
            String textData = multipartRequest.getParameter("textData");

            CompletableFuture.runAsync(() -> {
                // 这里假设已经有对应的dataReceiverService实例化并注入
                dataReceiverService.processTextData(textData);
            });

            responseMap.put("status", "success");
            responseMap.put("message", "文本数据接收成功，正在处理...");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("status", "error");
            responseMap.put("message", "接收文本数据时出错: " + e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 接收图片数据
    @PostMapping("/image")
    public ResponseEntity<Map<String, Object>> receiveImageData(HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            // 将请求转换为MultipartHttpServletRequest类型，以便处理form-data格式
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            // 获取form-data中名为"file"的图片文件数据
            MultipartFile imageFile = multipartRequest.getFile("file");

            CompletableFuture.runAsync(() -> {
                // 这里假设已经有对应的dataReceiverService实例化并注入
                dataReceiverService.processImageData(imageFile);
            });

            responseMap.put("status", "success");
            responseMap.put("message", "图片数据接收成功，正在处理...");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("status", "error");
            responseMap.put("message", "接收图片数据时出错: " + e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 接收音频数据
    @PostMapping("/audio")
    public ResponseEntity<Map<String, Object>> receiveAudioData(HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            // 将请求转换为MultipartHttpServletRequest类型，以便处理form-data格式
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            // 获取form-data中名为"file"的音频文件数据
            MultipartFile audioFile = multipartRequest.getFile("file");

            CompletableFuture.runAsync(() -> {
                // 这里假设已经有对应的dataReceiverService实例化并注入
                dataReceiverService.processAudioData(audioFile);
            });

            responseMap.put("status", "success");
            responseMap.put("message", "音频数据接收成功，正在处理...");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("status", "error");
            responseMap.put("message", "接收音频数据时出错: " + e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 接收视频数据
    @PostMapping("/video")
    public ResponseEntity<Map<String, Object>> receiveVideoData(HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            // 将请求转换为MultipartHttpServletRequest类型，以便处理form-data格式
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            // 获取form-data中名为"file"的视频文件数据
            MultipartFile videoFile = multipartRequest.getFile("file");

            dataReceiverService.enqueueVideoData(videoFile);

            responseMap.put("status", "success");
            responseMap.put("message", "视频数据已加入处理队列，正在等待处理...");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("status", "error");
            responseMap.put("message", "接收视频数据时出错: " + e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}