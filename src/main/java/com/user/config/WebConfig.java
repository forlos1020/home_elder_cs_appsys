package com.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: LSH
 * @Date: 2023/6/15-06-15-11:45
 * @Description: com.SwordBBS.config
 * @Version: 1.0
 */
@Component
public class WebConfig {

    @Value("${spring.mail.username:}")
    private String sendUsername;

    @Value("${admin.emails:}")
    private String adminEmails;

    public String getInnerApiAppKey() {
        return innerApiAppKey;
    }

    public String getInnerApiAppSecret() {
        return innerApiAppSecret;
    }

    @Value("${inner.api.appKey:}")
    private String innerApiAppKey;

    @Value("${inner.api.appSecret:}")
    private String innerApiAppSecret;

    public String getAdminEmails() {
        return adminEmails;
    }

    public String getSendUsername() {
        return sendUsername;
    }
    @Value("${project.folder:}")
    private String projectFolder;

    public String getProjectFolder() {
        return projectFolder;
    }
}
