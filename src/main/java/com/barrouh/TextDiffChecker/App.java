package com.barrouh.TextDiffChecker;

import java.io.IOException;
import java.util.ArrayList;

import com.barrouh.TextDiffChecker.beans.Utils;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	TextDiffChecker test = new TextDiffChecker();
    	test.findDifferences();
    	
    	/*Utils testutils = new Utils();
    	System.out.println(testutils.readFromFile("C:/Users/mbarrouh/Desktop/testtext.txt"));
    	*/
    }
}
