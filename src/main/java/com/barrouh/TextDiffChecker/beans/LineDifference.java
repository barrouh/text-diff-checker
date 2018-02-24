package com.barrouh.TextDiffChecker.beans;

import java.util.ArrayList;
/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class LineDifference {

	private int line ;
	
	private IsLineDiff isDiff ;
	
	private String lineValue;
	
	private  ArrayList<Difference> differences ;

	public LineDifference(int line, IsLineDiff isDiff,String lineValue) {
		super();
		this.line = line;
		this.isDiff = isDiff;
		this.lineValue=lineValue;
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

	public String getLineValue() {
		return lineValue;
	}

	public ArrayList<Difference> getDifferences() {
		return differences;
	}

	
	
	
	
	
	
}
