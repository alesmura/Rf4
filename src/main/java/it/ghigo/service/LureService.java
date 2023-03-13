package it.ghigo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ghigo.model.FishCatch;
import it.ghigo.model.Lure;
import it.ghigo.model.LureCatch;
import it.ghigo.model.LureFishCatchQueryResult;
import it.ghigo.repository.CatchRepository;
import it.ghigo.repository.FishRepository;
import it.ghigo.repository.LureRepository;

@Service
public class LureService {

	@Autowired
	private CatchRepository catchRepository;

	@Autowired
	private LureRepository lureRepository;

	@Autowired
	private FishRepository fishRepository;

	public List<Lure> findAll() {
		List<Lure> lureList = new ArrayList<>();
		lureRepository.findAll().forEach(l -> lureList.add(l));
		Collections.sort(lureList);
		return lureList;
	}

	public List<LureCatch> findLureFishCatchList() {
		// Read
		List<LureFishCatchQueryResult> qrList = catchRepository.findLureFishCatchList();
		// Grouping catches by lure
		Map<String, List<LureFishCatchQueryResult>> catchPerLureMap = qrList.stream()
				.collect(Collectors.groupingBy(LureFishCatchQueryResult::getLureName));
		// Transform
		List<LureCatch> retList = new ArrayList<>();
		catchPerLureMap.keySet().stream().forEach(lureKey -> retList.add(//
				new LureCatch(lureKey, //
						catchPerLureMap.get(lureKey).stream() //
								.map(qr -> new FishCatch(qr.getFishName(), qr.getCount()))
								.collect(Collectors.toList()))));
		Collections.sort(retList);
		//
		Map<String, String> fishIconMap = new HashMap<>();
		for (LureCatch lc : retList) {
			lc.setLureIcon(lureRepository.findById(lc.getLureName()).get().getIcon());
			for (FishCatch fc : lc.getFishCatchList()) {
				String fishIcon = fishIconMap.get(fc.getFishName());
				if (StringUtils.isBlank(fishIcon)) {
					fishIcon = fishRepository.findById(fc.getFishName()).get().getIcon();
					fishIconMap.put(fc.getFishName(), fishIcon);
				}
				fc.setFishIcon(fishIcon);
			}
		}
		//
		return retList;
	}
}