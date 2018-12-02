package com.barrouh.textdiffchecker.beans;

/**
 *  Enum for Type of Difference Removal or Addition
 *  
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public enum DiffType {

	REMOVAL("Removal"),
	ADDITION("Addition"),
	EQUAL("Equal");

    private String type;

    DiffType(final String type) {
        this.type = type;
    }
    
    public String getDiffType() {
        return type;
    }
}
