package com.ruoyi.org;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.ruoyi.org.mapper")
public class OrgApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrgApplication.class, args);
        System.out.println("🎉 ruoyi-org 启动成功！");
    }
}
