package it.ghigo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import it.ghigo.model.Catch;

public interface CatchRepository extends CrudRepository<Catch, Long> {
	public Optional<Catch> getByRegionAndLocationNameAndFishNameAndWeightKgAndLureNameAndDt(//
			String region, String locationName, String fishName, double weightKg, String lureName, Date dt);

	public List<Catch> findByLocationNameContainingIgnoreCaseAndFishNameContainingIgnoreCaseAndLureNameContainingIgnoreCaseAndDtGreaterThanEqual(
			String locationName, String fishName, String lureName, @Temporal Date dt);

	public List<Catch> findByLocationNameContainingIgnoreCaseAndFishNameIgnoreCaseAndLureNameContainingIgnoreCaseAndDtGreaterThanEqual(
			String locationName, String fishName, String lureName, @Temporal Date dt);

	public List<Catch> findByLocationNameContainingIgnoreCaseAndFishNameIgnoreCaseAndLureNameIgnoreCaseAndDtGreaterThanEqual(
			String locationName, String fishName, String lureName, @Temporal Date dt);

	public List<Catch> findByLocationNameContainingIgnoreCaseAndFishNameContainingIgnoreCaseAndLureNameIgnoreCaseAndDtGreaterThanEqual(
			String locationName, String fishName, String lureName, @Temporal Date dt);
}