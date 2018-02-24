package com.barrouh.TextDiffChecker.beans;

/*

 Enum for Type of Difference Removal or Addition
 
 */
/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public enum IsLineDiff {

	YES(true),
	NO(false);
	
    private boolean flag;

    IsLineDiff(boolean flag) {
        this.flag = flag;
    }

    public boolean LineDiff() {
        return flag;
    }
	
	
}
