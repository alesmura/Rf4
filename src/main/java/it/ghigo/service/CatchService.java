package it.ghigo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import it.ghigo.model.Catch;
import it.ghigo.model.Fish;
import it.ghigo.model.Location;
import it.ghigo.model.Lure;
import it.ghigo.model.parameter.CatchSearchParameter;
import it.ghigo.repository.CatchFinder;
import it.ghigo.repository.CatchRepository;
import it.ghigo.repository.FishRepository;
import it.ghigo.repository.LocationRepository;
import it.ghigo.repository.LureRepository;

@Service
public class CatchService {

	@Autowired
	private CatchRepository catchRepository;

	@Autowired
	private CatchFinder catchFinder;

	@Autowired
	private FishRepository fishRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LureRepository lureRepository;

	public Catch create(Catch c) {
		//
		Optional<Fish> fishOpt = fishRepository.findById(c.getFish().getName());
		if (!fishOpt.isPresent())
			fishRepository.save(c.getFish());
		Optional<Location> locationOpt = locationRepository.findById(c.getLocation().getName());
		if (!locationOpt.isPresent())
			locationRepository.save(c.getLocation());
		Optional<Lure> lureOpt = lureRepository.findById(c.getLure().getName());
		if (!lureOpt.isPresent())
			lureRepository.save(c.getLure());
		//
		Optional<Catch> catchOpt = catchRepository.getByRegionAndLocationNameAndFishNameAndWeightKgAndLureNameAndDt(//
				c.getRegion(), c.getLocation().getName(), c.getFish().getName(), c.getWeightKg(), c.getLure().getName(),
				c.getDt());
		if (catchOpt.isPresent())
			return catchOpt.get();
		return catchRepository.save(c);
	}

	public Page<Catch> findByCatchSearchParameter(CatchSearchParameter catchSearchParameter) throws Exception {
		return catchFinder.findByCatchSearchParameter(catchSearchParameter);
	}
}