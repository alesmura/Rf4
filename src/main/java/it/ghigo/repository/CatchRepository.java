package it.ghigo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.ghigo.model.Catch;

public interface CatchRepository extends CrudRepository<Catch, Long> {
	public Optional<Catch> getByRegionAndLocationAndFishNameAndWeightKgAndLureAndDt(//
			String region, String location, String fishName, double weightKg, String lure, Date dt);

	public List<Catch> findByLocationContainingIgnoreCaseAndFishNameContainingIgnoreCaseAndLureContainingIgnoreCase(
			String location, String fishName, String lure);
}