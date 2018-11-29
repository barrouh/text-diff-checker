package com.barrouh.textdiffchecker.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class FinalDifferences {
	
	private List<LineDifference> originalTextDiffs;
	
	private List<LineDifference> changedTextDiffs;

	public FinalDifferences(){
		this.originalTextDiffs = new ArrayList<>();
        this.changedTextDiffs  = new ArrayList<>();
	}
	
	public FinalDifferences(List<LineDifference> originalTextDiffs,List<LineDifference> changedTextDiffs) {
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
