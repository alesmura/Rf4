package it.ghigo.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "LOCATION")
public class Location implements Serializable, Comparable<Location> {
	//
	private static final long serialVersionUID = 1L;
	//
	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Location oth) {
		return this.getName().compareTo(oth.getName());
	}
}