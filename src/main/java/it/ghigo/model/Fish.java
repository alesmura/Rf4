package it.ghigo.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "FISH")
public class Fish implements Serializable, Comparable<Fish> {
	//
	private static final long serialVersionUID = 1L;
	//
	@Id
	private String name;
	private String icon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public int compareTo(Fish oth) {
		return this.getName().compareTo(oth.getName());
	}
}