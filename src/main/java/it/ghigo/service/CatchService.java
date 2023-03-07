package it.ghigo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import it.ghigo.model.Catch;
import it.ghigo.repository.CatchRepository;

public class CatchService {

	@Autowired
	private CatchRepository catchRepository;

	public Catch create(Catch c) {
		Optional<Catch> opt = catchRepository.getByRegionAndLocationAndFishAndWeightKgAndLureAndDt(//
				c.getRegion(), c.getLocation(), c.getFish(), c.getWeightKg(), c.getLure(), c.getDt());
		if (opt.isPresent())
			return opt.get();
		return catchRepository.save(c);
	}
}