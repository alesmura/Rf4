package it.ghigo.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import it.ghigo.model.Catch;
import it.ghigo.service.CatchService;

public class Rf4Writer implements ItemWriter<Catch> {

	@Autowired
	private CatchService catchService;

	@Override
	public void write(Chunk<? extends Catch> chunk) throws Exception {
		for (Catch cattura : chunk) {
			catchService.create(cattura);
		}
	}
}