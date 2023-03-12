package it.ghigo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "CATCH")
public class Catch implements Serializable, Comparable<Catch> {
	private static final long serialVersionUID = 1L;
	//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String region;
	@OneToOne
	private Location location;
	@OneToOne
	private Fish fish;
	private double weightKg;
	@OneToOne
	private Lure lure;
	private Date dt;

	//
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Fish getFish() {
		return fish;
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}

	public double getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(double weightKg) {
		this.weightKg = weightKg;
	}

	public Lure getLure() {
		return lure;
	}

	public void setLure(Lure lure) {
		this.lure = lure;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public int compareTo(Catch oth) {
		int ret = this.getLocation().compareTo(oth.getLocation());
		if (ret == 0)
			ret = this.getFish().getName().compareTo(oth.getFish().getName());
		if (ret == 0)
			ret = -Double.compare(this.getWeightKg(), oth.getWeightKg());
		if (ret == 0)
			ret = this.getDt().compareTo(oth.getDt());
		if (ret == 0)
			ret = this.getLure().compareTo(oth.getLure());
		return ret;
	}
}