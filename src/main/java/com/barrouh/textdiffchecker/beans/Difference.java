package com.barrouh.textdiffchecker.beans;

/**
 * This class created to store the value and the type of difference
 * 
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */

public class Difference {
	
	private DiffType type ;
	
	private String differenceValue ;

	public Difference(final DiffType type, final String differenceValue) {
		this.type = type;
		this.differenceValue = differenceValue;
	}

	public DiffType getType() {
		return type;
	}

	public void setType(final DiffType type) {
		this.type = type;
	}

	public String getDifferenceValue() {
		return differenceValue;
	}

	public void setDifferenceValue(final String differenceValue) {
		this.differenceValue = differenceValue;
	}	
		
}
