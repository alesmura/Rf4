package it.ghigo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.ghigo.model.Lure;
import it.ghigo.model.LureFishCatchQueryResult;

public interface LureRepository extends CrudRepository<Lure, String> {
	@Query(value = "select new it.ghigo.model.LureFishCatchQueryResult(c.lure.name, c.fish.name, count(1)) from Catch c group by c.lure.name, c.fish.name")
	public List<LureFishCatchQueryResult> findLureFishCatchList();
}