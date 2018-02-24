package com.barrouh.TextDiffChecker.beans;

public class Difference {
	
	private DiffType type ;
	
	private String difference ;

	public Difference(DiffType type, String difference) {
		super();
	
		this.type = type;
		this.difference = difference;
	}

	public DiffType getType() {
		return type;
	}

	public void setType(DiffType type) {
		this.type = type;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}
	
	
	
	
}
