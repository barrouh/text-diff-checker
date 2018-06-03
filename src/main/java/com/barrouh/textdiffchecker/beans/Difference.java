package com.barrouh.textdiffchecker.beans;

/**
 * this class created to store the value and the type of difference
 * 
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */

public class Difference {
	
	/**
	* the type of the difference 
	*/
	private DiffType type ;
	
	/**
	* the string value of the difference 
	*/
	private String differenceValue ;

	/**
	* the param constructor of the Difference class 
	*/
	public Difference(final DiffType type, final String differenceValue) {
		super();
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
