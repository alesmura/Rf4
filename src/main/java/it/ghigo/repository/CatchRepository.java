package it.ghigo.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.ghigo.model.Catch;

@Repository
public interface CatchRepository extends CrudRepository<Catch, Long> {
	public Optional<Catch> findByRegionAndLocationAndFishAndWeightKgAndLureAndDt(//
			String region, String location, String fish, double weightKg, String lure, Date dt);
}