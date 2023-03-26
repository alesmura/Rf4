package it.ghigo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.ghigo.model.Catch;
import it.ghigo.model.Count;
import it.ghigo.model.LureFishCatchQueryResult;

public interface CatchRepository extends CrudRepository<Catch, Long> {
	public Optional<Catch> getByRegionAndLocationNameAndFishNameAndWeightKgAndLureNameAndDt(//
			String region, String locationName, String fishName, double weightKg, String lureName, Date dt);

	@Query(value = "select new it.ghigo.model.LureFishCatchQueryResult(c.lure.name, c.fish.name, count(1)) from Catch c group by c.lure.name, c.fish.name order by c.lure.name, c.fish.name")
	public List<LureFishCatchQueryResult> findLureFishCatchList();

	@Query(value = "select new it.ghigo.model.Count(c.fish.name, count(1)) from Catch c group by c.fish.name order by count(1) desc")
	public List<Count> countPerFishList();
}