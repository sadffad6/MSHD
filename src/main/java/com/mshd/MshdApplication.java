package com.mshd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mshd.mapper")
public class MshdApplication {

    public static void main(String[] args) {
        SpringApplication.run(MshdApplication.class, args);
    }

}
