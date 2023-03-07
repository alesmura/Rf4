package it.ghigo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import it.ghigo.model.Catch;
import it.ghigo.service.CatchService;

@Configuration
public class Rf4Batch {
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private JobLauncher jobLauncher;

	@Scheduled(fixedDelay = 300_000)
	public void run() {
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(getRf4Job(), jobParameters);
		} catch (Exception ex) {
		}
	}

	@Bean
	public Job getRf4Job() {
		return new JobBuilder("rf4Job", jobRepository) //
				.incrementer(new RunIdIncrementer()) //
				.listener(getListener()) //
				.flow(getStep()) //
				.end() //
				.build();
	}

	@Bean
	public Step getStep() {
		return new StepBuilder("step", jobRepository) //
				.<String, Catch>chunk(1000, transactionManager)//
				.reader(getReader()) //
				.processor(getProcessor()) //
				.writer(getWriter()) //
				.build();
	}

	@Bean
	public Rf4JobExecutionListener getListener() {
		return new Rf4JobExecutionListener();
	}

	@Bean
	public Rf4Reader getReader() {
		return new Rf4Reader();
	}

	@Bean
	public Rf4Processor getProcessor() {
		return new Rf4Processor();
	}

	@Bean
	public Rf4Writer getWriter() {
		return new Rf4Writer();
	}

	@Bean
	public CatchService getCatchService() {
		return new CatchService();
	}
}