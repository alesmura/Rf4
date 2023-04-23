package it.ghigo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

import it.ghigo.model.Catch;
import it.ghigo.service.CatchService;

@Configuration
public class Rf4Batch {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Environment env;

	@Scheduled(fixedDelay = 300_000)
	public void run() {
		if (Boolean.valueOf(env.getProperty("my.stop-batch")))
			return;
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
			{
				Job rf4Job = jobBuilderFactory.get("rf4Job") //
						.incrementer(new RunIdIncrementer()) //
						.listener(getListener()) //
						.flow(getStep()) //
						.end() //
						.build();
				jobLauncher.run(rf4Job, jobParameters);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Step getStep() {
		return stepBuilderFactory.get("step") //
				.<String, Catch>chunk(1000)//
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