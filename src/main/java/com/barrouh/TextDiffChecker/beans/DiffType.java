package com.barrouh.TextDiffChecker.beans;

/*

 Enum for Type of Difference Removal or Addition
 
 */
/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public enum DiffType {

	REMOVAL("Removal"),
	ADDITION("Addition"),
	EQUAL("Equal");
	
    private String type;

    DiffType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
	
	
}
