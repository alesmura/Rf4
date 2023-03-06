package it.ghigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.ghigo.model.Catch;
import it.ghigo.repository.CatchRepository;

@Controller
public class Rf4Controller {

	@Autowired
	private CatchRepository repository;

	@GetMapping("/api/all")
	@ResponseBody
	public Iterable<Catch> getAll() {

		return repository.findAll();
	}
}
