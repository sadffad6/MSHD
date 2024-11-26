package org.example.mhsd.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DataReceiverService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 示例：处理文本数据（同步或异步，根据具体业务逻辑）
    public void processTextData(String textData) {
        // 处理文本数据逻辑
    }

    // 示例：处理图片数据（同步或异步，根据具体业务逻辑）
    public void processImageData(MultipartFile file) {
        // 处理图片数据逻辑
    }

    // 示例：处理音频数据（同步或异步，根据具体业务逻辑）
    public void processAudioData(MultipartFile file) {
        // 处理音频数据逻辑
    }

    // 将视频数据发送到RabbitMQ消息队列
    public void enqueueVideoData(MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes(); // 简化示例，实际应考虑大文件流处理
            rabbitTemplate.convertAndSend("videoQueue", fileBytes);
        } catch (IOException e) {
            // 处理文件读取异常
            e.printStackTrace();
        }
    }
}