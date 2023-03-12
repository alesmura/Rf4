package it.ghigo.repository;

import org.springframework.data.repository.CrudRepository;

import it.ghigo.model.Lure;

public interface LureRepository extends CrudRepository<Lure, String> {
}