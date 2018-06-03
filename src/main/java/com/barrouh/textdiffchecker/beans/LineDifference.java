package com.barrouh.textdiffchecker.beans;

import java.util.List;

/**
 * this class added to store the differences in each line 
 * 
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */

public class LineDifference {

	/**
	* the index of the line 
	*/
	private int line ;
	
	/**
	* this parameter added to know if the line is changed or no 
	*/
	private IsLineDiff isDiff ;
	
	/**
	* the string value of the line 
	*/
	private String lineValue;
	
	/**
	*  the list of all differences in one line , word by word 
	*/
	private List<Difference> differencesList ;

	/**
	* 
	*/
	public LineDifference(final int line, final IsLineDiff isDiff, final String lineValue) {
		super();
		this.line = line;
		this.isDiff = isDiff;
		this.lineValue=lineValue;
	}
	
	/**
	* 
	*/
	public LineDifference(final int line, final IsLineDiff isDiff, final List<Difference> differencesList) {
		super();
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
