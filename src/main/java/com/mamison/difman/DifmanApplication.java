package com.mamison.difman;

import com.mamison.difman.entity.Account;
import com.mamison.difman.entity.Report;
import com.mamison.difman.repository.ReportRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@SpringBootApplication
@EnableScheduling
public class DifmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DifmanApplication.class, args);

	}
}
