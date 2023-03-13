package it.ghigo.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class LureCatch implements Serializable, Comparable<LureCatch> {
	private static final long serialVersionUID = 1L;
	//
	private String lureName;
	private String lureIcon;
	private long count;
	private List<FishCatch> fishCatchList;

	//
	public LureCatch(String lureName, List<FishCatch> fishCatchList) {
		super();
		this.setLureName(lureName);
		Collections.sort(fishCatchList);
		this.setFishCatchList(fishCatchList);
	}

	public LureCatch(String lureName, long count) {
		super();
		this.setLureName(lureName);
		this.setCount(count);
	}

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

	public String getLureIcon() {
		return lureIcon;
	}

	public void setLureIcon(String lureIcon) {
		this.lureIcon = lureIcon;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		return this.getLureName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof LureCatch))
			return false;
		return this.getLureName().equals(((LureCatch) obj).getLureName());
	}

	@Override
	public int compareTo(LureCatch oth) {
		int ret = -Long.compare(this.getCount(), oth.getCount());
		if (ret == 0)
			ret = StringUtils.compare(this.getLureName(), oth.getLureName());
		return ret;
	}
}