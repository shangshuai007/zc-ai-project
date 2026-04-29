package com.zc.biz;

import com.zc.framework.base.provider.system.config.BaseServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.zc.**"})
@EnableScheduling
@MapperScan(basePackages = {"com.zc.*.mapper", "com.**.mapper"})
@EnableFeignClients(basePackages = {"com.zc.**"})
public class ServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
        System.out.println("<<<<<<<<<<<<BusinessServer启动成功>>>>>>>>>>>>");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        SpringApplicationBuilder sources = builder.sources(ServiceApplication.class);
        System.out.println("SERVICE服务启动成功");
        return sources;
    }
}
