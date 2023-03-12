package it.ghigo.model.parameter;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class CatchSearchParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private String location = "";
	private boolean exactFishName = false;
	private String fishName = "";
	private String lure = "";
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dt;

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

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public boolean isExactFishName() {
		return exactFishName;
	}

	public void setExactFishName(boolean exactFishName) {
		this.exactFishName = exactFishName;
	}

	public boolean isEmpty() {
		if (StringUtils.isNotBlank(getLocation()))
			return false;
		if (StringUtils.isNotBlank(getFishName()))
			return false;
		if (StringUtils.isNotBlank(getLure()))
			return false;
		if (getDt() != null)
			return false;
		return true;
	}
}