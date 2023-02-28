package com.dew.securityapp;

import com.dew.securityapp.config.VaultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VaultConfig.class)
@Slf4j
public class SecurityDemoApplication  implements CommandLineRunner {

	private  VaultConfig vaultConfig;

	public SecurityDemoApplication(VaultConfig vaultConfig) {
		this.vaultConfig = vaultConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("------------properties---------");
		log.info("Username : "+vaultConfig.getUsername());
		log.info("Token : "+vaultConfig.getToken());
	}
}
