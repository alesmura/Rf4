package it.ghigo.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import it.ghigo.model.Catch;
import it.ghigo.service.CatchService;

public class Rf4Writer implements ItemWriter<Catch> {

	@Autowired
	private CatchService catchService;

	@Override
	public void write(List<? extends Catch> items) throws Exception {
		for (Catch cattura : items) {
			catchService.create(cattura);
		}
	}
}