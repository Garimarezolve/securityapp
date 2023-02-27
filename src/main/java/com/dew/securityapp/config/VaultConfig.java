package com.dew.securityapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("securityapp")
public class VaultConfig {

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    public String password;
}
