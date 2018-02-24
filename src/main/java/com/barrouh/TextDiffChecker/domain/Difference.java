package com.barrouh.TextDiffChecker.domain;

public class Difference {

	private int line ;
	
	private DiffType type ;
	
	private String difference ;

	public Difference(int line, DiffType type, String difference) {
		super();
		this.line = line;
		this.type = type;
		this.difference = difference;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
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
