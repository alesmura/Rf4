package it.ghigo.repository;

import org.springframework.data.repository.CrudRepository;

import it.ghigo.model.Fish;

public interface FishRepository extends CrudRepository<Fish, String> {
}