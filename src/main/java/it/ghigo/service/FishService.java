package it.ghigo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ghigo.model.Fish;
import it.ghigo.repository.FishRepository;

@Service
public class FishService {
	@Autowired
	private FishRepository fishRepository;

	public List<Fish> findAll() {
		List<Fish> fishList = new ArrayList<>();
		fishRepository.findAll().forEach(f -> fishList.add(f));
		Collections.sort(fishList);
		return fishList;
	}
}