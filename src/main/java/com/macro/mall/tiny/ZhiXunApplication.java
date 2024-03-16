package com.macro.mall.tiny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZhiXunApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhiXunApplication.class, args);
        System.out.println("Swagger:   http://localhost:8090/swagger-ui/index.html");
    }

}
