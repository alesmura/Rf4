package it.ghigo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.ghigo.model.Fish;
import it.ghigo.model.FishInfo;

public interface FishRepository extends CrudRepository<Fish, String> {
	@Query(value = "select new it.ghigo.model.FishInfo(c.fish, max(c.weightKg), avg(c.weightKg), min(c.weightKg), count(1)) from Catch c group by c.fish.name order by c.fish.name")
	public List<FishInfo> findAllFishInfo();
}