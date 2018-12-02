package com.barrouh.textdiffchecker.beans;

/**
 *  Enum for Type of Difference Removal or Addition
 *  
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public enum IsLineDiff {

	YES(true),
	NO(false);
	
   /**
    * the final value of enum 
    */
    private boolean flag;

    IsLineDiff(final boolean flag) {
        this.flag = flag;
    }

    /**
    * This method added to return the value of the enum
    * @return the value of enum
    */
    public boolean isLineDiff() {
        return flag;
    }
}
