package com.barrouh.TextDiffChecker.beans;

import java.util.ArrayList;
/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class FinalDifferences {
	
	private ArrayList<LineDifference> originalTextDiffs ,changedTextDiffs;

	public FinalDifferences(){
		
		originalTextDiffs = new ArrayList<LineDifference>();
        changedTextDiffs  = new ArrayList<LineDifference>();
	}
	
	public FinalDifferences(ArrayList<LineDifference> originalTextDiffs, ArrayList<LineDifference> changedTextDiffs) {
		super();
		this.originalTextDiffs = originalTextDiffs;
		this.changedTextDiffs = changedTextDiffs;
	}

	public ArrayList<LineDifference> getOriginalTextDiffs() {
		return originalTextDiffs;
	}

	public void setOriginalTextDiffs(ArrayList<LineDifference> originalTextDiffs) {
		this.originalTextDiffs = originalTextDiffs;
	}

	public ArrayList<LineDifference> getChangedTextDiffs() {
		return changedTextDiffs;
	}

	public void setChangedTextDiffs(ArrayList<LineDifference> changedTextDiffs) {
		this.changedTextDiffs = changedTextDiffs;
	}
	
	
	
}
