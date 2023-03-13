package it.ghigo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ghigo.model.Location;
import it.ghigo.repository.LocationRepository;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;

	public List<Location> findAll() {
		List<Location> locationList = new ArrayList<>();
		locationRepository.findAll().forEach(l -> locationList.add(l));
		Collections.sort(locationList);
		return locationList;
	}
}