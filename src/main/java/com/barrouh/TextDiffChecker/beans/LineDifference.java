package com.barrouh.TextDiffChecker.beans;

import java.util.ArrayList;

public class LineDifference {

	private int line ;
	
	private IsLineDiff isDiff ;
	
	private  ArrayList<Difference> differences ;

	public LineDifference(int line, IsLineDiff isDiff) {
		super();
		this.line = line;
		this.isDiff = isDiff;
	}
	
	public LineDifference(int line, IsLineDiff isDiff, ArrayList<Difference> differences) {
		super();
		this.line = line;
		this.isDiff = isDiff;
		this.differences = differences;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public IsLineDiff getIsDiff() {
		return isDiff;
	}

	public void setIsDiff(IsLineDiff isDiff) {
		this.isDiff = isDiff;
	}

	public ArrayList<Difference> getDifferences() {
		return differences;
	}

	
	
	
	
	
	
}
