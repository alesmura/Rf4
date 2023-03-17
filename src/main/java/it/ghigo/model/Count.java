package it.ghigo.model;

import java.io.Serializable;

public class Count extends java.util.AbstractMap.SimpleEntry<String, Long> implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	public Count(String key, Long count) {
		super(key, count);
	}
}
