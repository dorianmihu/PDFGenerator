package com.dorian.mihu.PDFReportCreator;

import com.dorian.mihu.PDFReportCreator.storage.StorageException;
import com.dorian.mihu.PDFReportCreator.storage.StorageProperties;
import com.dorian.mihu.PDFReportCreator.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PdfReportCreatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfReportCreatorApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return(args) -> storageService.init();
	}
}
