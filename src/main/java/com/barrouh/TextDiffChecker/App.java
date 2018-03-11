package com.barrouh.TextDiffChecker;

import java.io.IOException;


import com.barrouh.TextDiffChecker.beans.Utils;

/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Utils testutils = new Utils();
    	
    	String text1 , text2;
    	
    	text1 =testutils.readFromFile("src/test/resources/testcase1.txt");
        text2 =testutils.readFromFile("src/test/resources/testcase2.txt");

    	TextDiffChecker test = new TextDiffChecker(text1,text2);
    	testutils.convertToHtmlFile(test.getFinalDifferences(),"src/test/resources/");
    	//System.out.println(testutils.convertToHtml(test.getFinalDifferences()));
    	
    /*
    	String test1="test  test1";
    	TextDiffChecker test = new TextDiffChecker();
    	
    	ArrayList<String> res=test.convertStringToWords(test1);
    	
    	System.out.println(res.size());
    	
    	System.out.println("-"+res.get(0)+"- -"+res.get(1)+"-");
    */	
    	
    	
    	
    }
}
