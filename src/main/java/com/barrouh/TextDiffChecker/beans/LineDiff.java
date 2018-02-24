package com.barrouh.TextDiffChecker.beans;

/*

 Enum for Type of Difference Removal or Addition
 
 */

public enum LineDiff {

	YES(true),
	NO(false);
	
    private boolean flag;

    LineDiff(boolean flag) {
        this.flag = flag;
    }

    public boolean LineDiff() {
        return flag;
    }
	
	
}
