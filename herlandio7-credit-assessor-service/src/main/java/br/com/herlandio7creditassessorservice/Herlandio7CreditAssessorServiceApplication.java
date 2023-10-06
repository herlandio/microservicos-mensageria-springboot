package br.com.herlandio7creditassessorservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableRabbit
public class Herlandio7CreditAssessorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Herlandio7CreditAssessorServiceApplication.class, args);
	}

}
