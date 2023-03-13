package it.ghigo.model;

import java.io.Serializable;
import java.util.List;

public class LureCatch implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private String lureName;
	private List<FishCatch> fishCatchList;

	//
	public String getLureName() {
		return lureName;
	}

	public void setLureName(String lureName) {
		this.lureName = lureName;
	}

	public List<FishCatch> getFishCatchList() {
		return fishCatchList;
	}

	public void setFishCatchList(List<FishCatch> fishCatchList) {
		this.fishCatchList = fishCatchList;
	}
}