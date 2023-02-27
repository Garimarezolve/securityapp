package com.dew.securityapp.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {

    private final VaultConfig appConfiguration;

    @PostConstruct
    public void readConfigs() {
        log.info("-------------------------------------------`\n");
        log.info("Reading configuration {} - {} \n", appConfiguration.getPassword(), appConfiguration.getUsername());
        log.info("-------------------------------------------`\n");
    }

}