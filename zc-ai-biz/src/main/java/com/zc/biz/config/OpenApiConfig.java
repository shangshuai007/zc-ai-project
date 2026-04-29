package com.zc.biz.config;

import cn.dev33.satoken.config.SaTokenConfig;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Autowired
    private SaTokenConfig saTokenConfig;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${auth-filter.satoken:/doc.html}")
    private String saTokenFilter;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(saTokenConfig.getTokenName(),
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)  // 确保类型正确
                                        .in(SecurityScheme.In.HEADER)      // 确保位置正确
                                        .name(saTokenConfig.getTokenName())            // 确保名称正确
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList(saTokenConfig.getTokenName()))
                .info(new Info()
                        .title("业务服务 API")
                        .version("1.0.0")
                        .description("业务服务 API 文档")
                        .contact(new Contact()
                                .name("业务服务")
                                .email(applicationName + "@qq.com")
                                .url("https://" + applicationName + ".com")));
    }

    @Bean
    public GlobalOpenApiCustomizer globalOpenApiCustomizer() {
        String afterValue = this.saTokenFilter.replaceAll("\\s*|\r|\n|\t", "");
        List<String> uris = Arrays.asList(afterValue.split(","));
        return openApi -> {
            // 全局添加鉴权参数
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((s, pathItem) -> {
                    PathMatcher pathMatcher = new AntPathMatcher();
                    boolean isMatch = false;
                    for(String url : uris) {
                        if(pathMatcher.match(s, url)) {
                            isMatch = true;
                        }
                    }
                    if(!isMatch) {
                        // 接口添加鉴权参数
                        pathItem.readOperations()
                                .forEach(operation ->
                                        operation.addSecurityItem(new SecurityRequirement().addList(saTokenConfig.getTokenName()))
                                );
                    }
                });
            }
        };
    }

}
