package it.ghigo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FishInfo implements Serializable, Comparable<FishInfo> {

	private static final long serialVersionUID = 1L;
	//
	private Fish fish;
	private double maxWeight;
	private double avgWeight;
	private double minWeight;
	private long count;

	//
	public FishInfo(Fish fish, double maxWeight, double avgWeight, double minWeight, long count) {
		super();
		this.fish = fish;
		this.maxWeight = maxWeight;
		this.avgWeight = new BigDecimal(String.valueOf(avgWeight)).setScale(2, RoundingMode.HALF_UP).doubleValue();
		this.minWeight = minWeight;
		this.count = count;
	}

	//
	public Fish getFish() {
		return fish;
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public double getAvgWeight() {
		return avgWeight;
	}

	public void setAvgWeight(double avgWeight) {
		this.avgWeight = avgWeight;
	}

	public double getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(double minWeight) {
		this.minWeight = minWeight;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public int compareTo(FishInfo oth) {
		return this.getFish().compareTo(oth.getFish());
	}
}