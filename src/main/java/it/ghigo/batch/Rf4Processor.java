package it.ghigo.batch;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;

import it.ghigo.model.Catch;

public class Rf4Processor implements ItemProcessor<String, Catch> {
	public final static String SEP = "@@";
	private static SimpleDateFormat sdf = new SimpleDateFormat("d.MM.yy");

	@Override
	public Catch process(String item) throws Exception {
		String[] vals = item.split(SEP);
		//
		int i = 0;
		Catch cattura = new Catch();
		cattura.setRegion(vals[i++]);
		cattura.setLocation(vals[i++]);
		cattura.setFish(vals[i++]);
		cattura.setWeightKg(Double.valueOf(vals[i++]));
		cattura.setLure(vals[i++]);
		cattura.setDt(sdf.parse(vals[i++]));
		return cattura;
	}
}