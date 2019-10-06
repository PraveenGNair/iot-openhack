package com.openhack;

import com.openhack.service.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SparkApplication {

	@Autowired
	DataProcessor dataProcessor;

	public static void main(String[] args) {
		SpringApplication.run(SparkApplication.class, args);

	}

	@PostConstruct
	public void init(){
		dataProcessor.processIotData();
	}
}
