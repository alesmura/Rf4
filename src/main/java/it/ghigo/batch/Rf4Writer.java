package it.ghigo.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import it.ghigo.model.Catch;
import it.ghigo.service.CatchService;

public class Rf4Writer implements ItemWriter<Catch> {

	private static final Logger log = LoggerFactory.getLogger(Rf4Writer.class);

	@Autowired
	private CatchService catchService;

	@Override
	public void write(List<? extends Catch> items) throws Exception {
		long inizio = System.currentTimeMillis();
		log.info("Start Rf4Writer " + items.size() + " record");
		for (Catch cattura : items) {
			catchService.create(cattura);
		}
		log.info("End Rf4Writer -> " + (System.currentTimeMillis() - inizio));
	}
}