
package com.zc.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 文件服务
 * @author Ryan
 */


@SpringBootApplication(scanBasePackages = "com.zc")
@ComponentScan("com.zc.*****")
@Configuration
@MapperScan(basePackages = {"com.zc.**.mapper"})
@EnableDiscoveryClient
public class FileServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileServerApplication.class, args);
        System.out.println("文件服务启动成功");
    }

}
