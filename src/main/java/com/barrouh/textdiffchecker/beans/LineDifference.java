package com.barrouh.textdiffchecker.beans;

import java.util.List;

/**
 * this class added to store the differences in each line 
 * 
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class LineDifference {

	private int line ;
	
	private IsLineDiff isDiff ;
	
	private String lineValue;
	
	private List<Difference> differencesList ;

	public LineDifference(final int line, final IsLineDiff isDiff, final String lineValue) {
		this.line = line;
		this.isDiff = isDiff;
		this.lineValue=lineValue;
	}
	
	public LineDifference(final int line, final IsLineDiff isDiff, final List<Difference> differencesList) {
		this.line = line;
		this.isDiff = isDiff;
		this.differencesList = differencesList;
	}

	public int getLine() {
		return line;
	}

	public void setLine(final int line) {
		this.line = line;
	}

	public IsLineDiff getIsDiff() {
		return isDiff;
	}

	public void setIsDiff(final IsLineDiff isDiff) {
		this.isDiff = isDiff;
	}

	public String getLineValue() {
		return lineValue;
	}

	public void setLineValue(final String lineValue) {
		this.lineValue = lineValue;
	}

	public List<Difference> getDifferencesList() {
		return differencesList;
	}	
}
