package it.ghigo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import it.ghigo.repository.CatchFinder;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
public class Rf4Application {
	public static void main(String[] args) {
		SpringApplication.run(Rf4Application.class, args);
	}

	@Bean
	public CatchFinder getCatchFinder() {
		return new CatchFinder();
	}
}