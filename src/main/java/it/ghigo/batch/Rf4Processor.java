package it.ghigo.batch;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;

import it.ghigo.model.Catch;
import it.ghigo.model.Fish;

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
		//
		Fish fish = new Fish();
		fish.setName(vals[i++]);
		fish.setIcon(vals[i++]);
		cattura.setFish(fish);
		//
		cattura.setWeightKg(Double.valueOf(vals[i++]));
		cattura.setLure(vals[i++]);
		cattura.setDt(sdf.parse(vals[i++]));
		return cattura;
	}
}