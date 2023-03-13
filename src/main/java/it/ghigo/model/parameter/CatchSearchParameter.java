package it.ghigo.model.parameter;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class CatchSearchParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private String locationName = "";
	private boolean exactFishName = false;
	private String fishName = "";
	private boolean exactLureName = false;
	private String lureName = "";
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dt;

	//
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getFishName() {
		return fishName;
	}

	public void setFishName(String fishName) {
		this.fishName = fishName;
	}

	public String getLureName() {
		return lureName;
	}

	public void setLureName(String lureName) {
		this.lureName = lureName;
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

	public boolean isExactLureName() {
		return exactLureName;
	}

	public void setExactLureName(boolean exactLureName) {
		this.exactLureName = exactLureName;
	}

	public boolean isEmpty() {
		if (StringUtils.isNotBlank(getLocationName()))
			return false;
		if (StringUtils.isNotBlank(getFishName()))
			return false;
		if (StringUtils.isNotBlank(getLureName()))
			return false;
		if (getDt() != null)
			return false;
		return true;
	}
}