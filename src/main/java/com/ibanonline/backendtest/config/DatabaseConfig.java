package com.ibanonline.backendtest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ricardo Bernardino
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "database")
public class DatabaseConfig {
    private String user;
    private String pass = "";
    private String url;
}

