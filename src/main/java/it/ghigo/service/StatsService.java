package it.ghigo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ghigo.model.Count;
import it.ghigo.repository.CatchRepository;

@Service
public class StatsService {

	@Autowired
	private CatchRepository catchRepository;

	public List<Count> findAll() {
		List<Count> retList = new ArrayList<>();
		retList.add(new Count("Catch", catchRepository.count()));
		retList.addAll(catchRepository.countPerFishList());
		return retList;
	}
}