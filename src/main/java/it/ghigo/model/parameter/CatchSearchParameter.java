package it.ghigo.model.parameter;

import java.io.Serializable;

public class CatchSearchParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private String location = "";
	private String fishName = "";
	private String lure = "";

	//
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFishName() {
		return fishName;
	}

	public void setFishName(String fishName) {
		this.fishName = fishName;
	}

	public String getLure() {
		return lure;
	}

	public void setLure(String lure) {
		this.lure = lure;
	}
}