package com.example.bms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value = "com.example.bms.mapper")
public class BooksManagenmentSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(BooksManagenmentSystemApplication.class, args);
	}

}
