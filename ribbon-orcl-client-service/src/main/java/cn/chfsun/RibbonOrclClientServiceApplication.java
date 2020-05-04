package cn.chfsun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RibbonOrclClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonOrclClientServiceApplication.class, args);
	}

}
