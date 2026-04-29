package com.zc.base;

import com.zc.framework.base.provider.system.config.BaseServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication(scanBasePackages = {"com.zc.**", "com.anji.captcha.**"})
@BaseServer
@MapperScan(basePackages = { "com.**.mapper"})
public class BaseApplication  extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
        System.out.println("<<<<<<<<<<<<SystemServer启动成功>>>>>>>>>>>>");
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        SpringApplicationBuilder sources = builder.sources(BaseApplication.class);
        System.out.println("SYSTEM服务启动成功");
        return sources;
    }
}
