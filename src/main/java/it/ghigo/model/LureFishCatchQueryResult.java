package it.ghigo.model;

import java.io.Serializable;

public class LureFishCatchQueryResult implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private String lureName;
	private String fishName;
	private long count;

	public LureFishCatchQueryResult(String lureName, String fishName, long count) {
		super();
		this.lureName = lureName;
		this.fishName = fishName;
		this.count = count;
	}

	public String getLureName() {
		return lureName;
	}

	public void setLureName(String lureName) {
		this.lureName = lureName;
	}

	public String getFishName() {
		return fishName;
	}

	public void setFishName(String fishName) {
		this.fishName = fishName;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}