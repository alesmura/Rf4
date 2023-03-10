package it.ghigo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ghigo.model.Catch;
import it.ghigo.model.Fish;
import it.ghigo.model.parameter.CatchSearchParameter;
import it.ghigo.repository.CatchRepository;
import it.ghigo.repository.FishRepository;

@Service
public class CatchService {

	@Autowired
	private CatchRepository catchRepository;

	@Autowired
	private FishRepository fishRepository;

	public Catch create(Catch c) {
		//
		Optional<Fish> fishOpt = fishRepository.findById(c.getFish().getName());
		if (!fishOpt.isPresent())
			fishRepository.save(c.getFish());
		//
		Optional<Catch> catchOpt = catchRepository.getByRegionAndLocationAndFishNameAndWeightKgAndLureAndDt(//
				c.getRegion(), c.getLocation(), c.getFish().getName(), c.getWeightKg(), c.getLure(), c.getDt());
		if (catchOpt.isPresent())
			return catchOpt.get();
		return catchRepository.save(c);
	}

	public List<Catch> findByCatchSearchParameter(CatchSearchParameter catchSearchParameter) {
		if (catchSearchParameter.isEmpty())
			return new ArrayList<>();
		List<Catch> catchList = catchRepository
				.findByLocationContainingIgnoreCaseAndFishNameContainingIgnoreCaseAndLureContainingIgnoreCaseAndDtGreaterThanEqual(
						catchSearchParameter.getLocation(), catchSearchParameter.getFishName(),
						catchSearchParameter.getLure(), catchSearchParameter.getDt());
		Collections.sort(catchList);
		return catchList;
	}
}