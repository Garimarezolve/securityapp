package com.dew.securityapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SecurityDemoApplication {

//	private  VaultConfig vaultConfig;

//	public SecurityDemoApplication(VaultConfig vaultConfig) {
//		this.vaultConfig = vaultConfig;
//	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityDemoApplication.class, args);
	}

}
