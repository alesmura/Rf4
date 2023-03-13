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

import it.ghigo.model.Fish;
import it.ghigo.model.FishCatch;
import it.ghigo.model.LureCatch;
import it.ghigo.model.LureFishCatchQueryResult;
import it.ghigo.repository.CatchRepository;
import it.ghigo.repository.FishRepository;
import it.ghigo.repository.LureRepository;

@Service
public class FishService {

	@Autowired
	private CatchRepository catchRepository;

	@Autowired
	private FishRepository fishRepository;

	@Autowired
	private LureRepository lureRepository;

	public List<Fish> findAll() {
		List<Fish> fishList = new ArrayList<>();
		fishRepository.findAll().forEach(f -> fishList.add(f));
		Collections.sort(fishList);
		return fishList;
	}

	public List<FishCatch> findFishLureCatchList() {
		// Read
		List<LureFishCatchQueryResult> qrList = catchRepository.findLureFishCatchList();
		// Grouping catches by lure
		Map<String, List<LureFishCatchQueryResult>> fishPerLureMap = qrList.stream()
				.collect(Collectors.groupingBy(LureFishCatchQueryResult::getFishName));
		// Transform
		List<FishCatch> retList = new ArrayList<>();
		fishPerLureMap.keySet().stream().forEach(fishKey -> retList.add(//
				new FishCatch(fishKey, //
						fishPerLureMap.get(fishKey).stream() //
								.map(qr -> new LureCatch(qr.getLureName(), qr.getCount()))
								.collect(Collectors.toList()))));
		Collections.sort(retList);
		//
		Map<String, String> lureIconMap = new HashMap<>();
		for (FishCatch fc : retList) {
			fc.setFishIcon(fishRepository.findById(fc.getFishName()).get().getIcon());
			for (LureCatch lc : fc.getLureCatchList()) {
				String lureIcon = lureIconMap.get(lc.getLureName());
				if (StringUtils.isBlank(lureIcon)) {
					lureIcon = lureRepository.findById(lc.getLureName()).get().getIcon();
					lureIconMap.put(lc.getLureName(), lureIcon);
				}
				lc.setLureIcon(lureIcon);
			}
		}
		//
		return retList;
	}
}