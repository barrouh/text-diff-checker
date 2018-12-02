package com.barrouh.textdiffchecker.utils;

import java.util.Comparator;

import com.barrouh.textdiffchecker.beans.LineDifference;

public class MapComparator implements Comparator<LineDifference> {
	  public int compare(LineDifference a, LineDifference b) {
		   return 1 ;
		  }
}
