package com.dbs.springbatchdemo2;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.dbs.config", "com.dbs.service","com.dbs.listener","com.dbs.reader",
		"com.dbs.processor","com.dbs.writer","com.dbs.controller"})
@EnableAsync
public class SpringBatchDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchDemo2Application.class, args);
	}

}
