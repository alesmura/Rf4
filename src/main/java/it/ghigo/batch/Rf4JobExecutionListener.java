package it.ghigo.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;

public class Rf4JobExecutionListener implements org.springframework.batch.core.JobExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(Rf4JobExecutionListener.class);
	private long start;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("### Inizio rf4 ###");
		start = System.currentTimeMillis();

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("### Fine rf4 in " + (System.currentTimeMillis() - start) + " ###");
	}

}
