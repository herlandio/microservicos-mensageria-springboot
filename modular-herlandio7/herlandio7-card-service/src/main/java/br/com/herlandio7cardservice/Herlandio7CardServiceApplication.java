package br.com.herlandio7cardservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class Herlandio7CardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Herlandio7CardServiceApplication.class, args);
	}

}
