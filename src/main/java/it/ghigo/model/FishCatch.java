package it.ghigo.model;

import java.io.Serializable;

public class FishCatch implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private String fishName;
	private int count;

	//
	public String getFishName() {
		return fishName;
	}

	public void setFishName(String fishName) {
		this.fishName = fishName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
