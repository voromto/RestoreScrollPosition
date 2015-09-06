package com.example.restorescrollposition;

import java.util.ArrayList;
import java.util.List;

public class ExpandListGroup<T1, T2> {
	private T1 Data;
	private List<T2> Entities = new ArrayList<T2>();

	public ExpandListGroup(T1 data, List<T2> entities) {
		super();
		Data = data;
		Entities = entities;
	}

	public T1 getData() {
		return Data;
	}

	public List<T2> getItems() {
		return Entities;
	}
}