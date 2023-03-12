package it.ghigo.repository;

import org.springframework.data.repository.CrudRepository;

import it.ghigo.model.Location;

public interface LocationRepository extends CrudRepository<Location, String> {
}