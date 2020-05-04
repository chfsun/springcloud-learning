package cn.chfsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RibbonUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonUserServiceApplication.class, args);
	}

}
