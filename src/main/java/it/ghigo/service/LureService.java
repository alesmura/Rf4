package it.ghigo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ghigo.model.Lure;
import it.ghigo.model.LureFishCatchQueryResult;
import it.ghigo.repository.LureRepository;

@Service
public class LureService {
	@Autowired
	private LureRepository lureRepository;

	public List<Lure> findAll() {
		List<Lure> lureList = new ArrayList<>();
		lureRepository.findAll().forEach(l -> lureList.add(l));
		Collections.sort(lureList);
		return lureList;
	}

	public List<LureFishCatchQueryResult> findLureFishCatchList() {
		return lureRepository.findLureFishCatchList();
	}
}