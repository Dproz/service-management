package com.ceitechs.dproz.servicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.ceitechs.dproz")
@EnableDiscoveryClient
public class ServiceManagementAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceManagementAppApplication.class, args);
	}
}
