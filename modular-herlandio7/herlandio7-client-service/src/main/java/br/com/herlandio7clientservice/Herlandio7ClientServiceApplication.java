package br.com.herlandio7clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Herlandio7ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Herlandio7ClientServiceApplication.class, args);
	}

}
