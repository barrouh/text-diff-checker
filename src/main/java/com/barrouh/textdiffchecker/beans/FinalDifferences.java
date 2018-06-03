package com.barrouh.textdiffchecker.beans;

import java.util.ArrayList;
import java.util.List;
/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class FinalDifferences {
	
	/**
	* the list of the differences of the original text 
	*/
	private List<LineDifference> originalTextDiffs;
	
	/**
	* the list of the differences of the changed text 
	*/
	private List<LineDifference> changedTextDiffs;

	/**
	* the default constructor of the FinalDifferences class 
	*/
	public FinalDifferences(){
		originalTextDiffs = new ArrayList<LineDifference>();
        changedTextDiffs  = new ArrayList<LineDifference>();
	}
	
	/**
	* the param constructor of the FinalDifferences class 
	*/
	public FinalDifferences(final List<LineDifference> originalTextDiffs, final List<LineDifference> changedTextDiffs) {
		super();
		this.originalTextDiffs = originalTextDiffs;
		this.changedTextDiffs = changedTextDiffs;
	}

	public List<LineDifference> getOriginalTextDiffs() {
		return originalTextDiffs;
	}

	public void setOriginalTextDiffs(final List<LineDifference> originalTextDiffs) {
		this.originalTextDiffs = originalTextDiffs;
	}

	public List<LineDifference> getChangedTextDiffs() {
		return changedTextDiffs;
	}

	public void setChangedTextDiffs(final List<LineDifference> changedTextDiffs) {
		this.changedTextDiffs = changedTextDiffs;
	}
		
}
