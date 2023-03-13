package it.ghigo.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class FishCatch implements Serializable, Comparable<FishCatch> {
	private static final long serialVersionUID = 1L;
	//
	private String fishName;
	private String fishIcon;
	private long count;
	private List<LureCatch> lureCatchList;

	//
	public FishCatch(String fishName, List<LureCatch> lureCatchList) {
		super();
		this.setFishName(fishName);
		Collections.sort(lureCatchList);
		this.setLureCatchList(lureCatchList);
	}

	public FishCatch(String fishName, long count) {
		super();
		this.setFishName(fishName);
		this.setCount(count);
	}

	//
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

	public String getFishIcon() {
		return fishIcon;
	}

	public void setFishIcon(String fishIcon) {
		this.fishIcon = fishIcon;
	}

	public List<LureCatch> getLureCatchList() {
		return lureCatchList;
	}

	public void setLureCatchList(List<LureCatch> lureCatchList) {
		this.lureCatchList = lureCatchList;
	}

	@Override
	public int hashCode() {
		return this.getFishName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof FishCatch))
			return false;
		return this.getFishName().equals(((FishCatch) obj).getFishName());
	}

	@Override
	public int compareTo(FishCatch oth) {
		int ret = -Long.compare(this.getCount(), oth.getCount());
		if (ret == 0)
			ret = StringUtils.compare(this.getFishName(), oth.getFishName());
		return ret;
	}
}