package it.ghigo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ghigo.model.Catch;
import it.ghigo.model.Fish;
import it.ghigo.model.Location;
import it.ghigo.model.Lure;
import it.ghigo.model.parameter.CatchSearchParameter;
import it.ghigo.repository.CatchRepository;
import it.ghigo.repository.FishRepository;
import it.ghigo.repository.LocationRepository;
import it.ghigo.repository.LureRepository;

@Service
public class CatchService {

	@Autowired
	private CatchRepository catchRepository;

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

	public List<Catch> findByCatchSearchParameter(CatchSearchParameter catchSearchParameter) throws Exception {
		if (catchSearchParameter.isEmpty())
			return new ArrayList<>();
		List<Catch> catchList;
		Date dt = catchSearchParameter.getDt();
		if (dt == null)
			dt = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
		//
		boolean searchExactFish = StringUtils.isNotBlank(catchSearchParameter.getFishName())
				&& catchSearchParameter.isExactFishName();
		boolean searchExactLure = StringUtils.isNotBlank(catchSearchParameter.getLureName())
				&& catchSearchParameter.isExactLureName();
		//
		if (searchExactFish && searchExactLure) {
			catchList = catchRepository
					.findByLocationNameContainingIgnoreCaseAndFishNameIgnoreCaseAndLureNameIgnoreCaseAndDtGreaterThanEqual(
							catchSearchParameter.getLocationName(), catchSearchParameter.getFishName(),
							catchSearchParameter.getLureName(), dt);
		} else if (!searchExactFish && searchExactLure) {
			catchList = catchRepository
					.findByLocationNameContainingIgnoreCaseAndFishNameContainingIgnoreCaseAndLureNameIgnoreCaseAndDtGreaterThanEqual(
							catchSearchParameter.getLocationName(), catchSearchParameter.getFishName(),
							catchSearchParameter.getLureName(), dt);
		} else if (searchExactFish && !searchExactLure) {
			catchList = catchRepository
					.findByLocationNameContainingIgnoreCaseAndFishNameIgnoreCaseAndLureNameContainingIgnoreCaseAndDtGreaterThanEqual(
							catchSearchParameter.getLocationName(), catchSearchParameter.getFishName(),
							catchSearchParameter.getLureName(), dt);
		} else {
			catchList = catchRepository
					.findByLocationNameContainingIgnoreCaseAndFishNameContainingIgnoreCaseAndLureNameContainingIgnoreCaseAndDtGreaterThanEqual(
							catchSearchParameter.getLocationName(), catchSearchParameter.getFishName(),
							catchSearchParameter.getLureName(), dt);
		}
		Collections.sort(catchList);
		return catchList;
	}
}