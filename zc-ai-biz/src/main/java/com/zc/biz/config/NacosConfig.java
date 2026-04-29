package com.zc.biz.config;

import com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * @author Chen Long
 * @version 1.0
 * @className NacosConfig
 * @description Nacos配置
 * @date 2021/12/26
 */
@Component
@Slf4j
@ConditionalOnDiscoveryEnabled
public class NacosConfig implements ApplicationRunner {

    @Autowired(required = false)
    private NacosAutoServiceRegistration registration;

    @Value("${server.port}")
    Integer port;

    @Value("${spring.profiles.active}")
    String profile;

    @Override
    public void run(ApplicationArguments args) {
        if (registration != null && port != null) {
            Integer tomcatPort = port;
            try {
                tomcatPort = new Integer(getMiddlewarePort());
            } catch (Exception e) {
                //nothing todos
//                if (profile.indexOf("tongweb") >= 0) {
//                    tomcatPort = 8088;
//                }
            }
            registration.setPort(tomcatPort);
            registration.start();
        }
    }

    /**
     * 获取外部tomcat端口
     */
    public String getMiddlewarePort() throws Exception {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        String port = objectNames.iterator().next().getKeyProperty("port");
        log.info("getMiddlewarePort port is : , {}", port);
        return port;
    }

}
