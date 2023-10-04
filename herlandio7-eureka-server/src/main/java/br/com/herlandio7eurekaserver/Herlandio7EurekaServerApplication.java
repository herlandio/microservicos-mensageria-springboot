package br.com.herlandio7eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Herlandio7EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Herlandio7EurekaServerApplication.class, args);
	}

}
