package com.showtime.jkgl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.showtime.jkgl.mapper")
public class JkglApplication {

	public static void main(String[] args) {
		SpringApplication.run(JkglApplication.class, args);
	}
}
