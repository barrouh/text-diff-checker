package com.barrouh.TextDiffChecker;

import java.util.ArrayList;
import java.util.Arrays;

import com.barrouh.TextDiffChecker.beans.DiffType;
import com.barrouh.TextDiffChecker.beans.Difference;
import com.barrouh.TextDiffChecker.beans.FinalDifferences;
import com.barrouh.TextDiffChecker.beans.IsLineDiff;
import com.barrouh.TextDiffChecker.beans.LineDifference;

/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class TextDiffChecker {

	private String originalText ="holla \r\n" + "good\r\n" + "test" , 
			       changedText  ="hi\r\n"    + "fine\r\n" ;
	
	private FinalDifferences finalDiffs = new FinalDifferences();
	
	public TextDiffChecker() {
		
	}

	public TextDiffChecker(String originalText, String changedText) {
		super();
		this.originalText = originalText;
		this.changedText = changedText;
	}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public String getChangedText() {
		return changedText;
	}

	public void setChangedText(String changedText) {
		this.changedText = changedText;
	}

	public FinalDifferences getDifferences() {
		return finalDiffs;
	}

	public void findDifferences(){
		// convert input strings to string lines 
		ArrayList<String>  originalTextLines= convertStringToLines(originalText);
		ArrayList<String>  changedTextLines =convertStringToLines(changedText);
		//  avoid null pointer exception error
		checkIfCountLinesEquals(originalTextLines,changedTextLines);
		// check lines differences 
		for(int i=0;i<originalTextLines.size();i++){
			
			if(originalTextLines.get(i).equalsIgnoreCase(changedTextLines.get(i))){
				// add the same lines to final diffs object 
				finalDiffs.getOriginalTextDiffs().add(new LineDifference(i,IsLineDiff.NO,originalTextLines.get(i)));
				finalDiffs.getChangedTextDiffs().add(new LineDifference(i,IsLineDiff.NO,changedTextLines.get(i)));
			   }   
			else{
				
				 final ArrayList<Difference> originalWordsDifferences = new ArrayList<Difference>() ;
				 final ArrayList<Difference> changedWordsDifferences = new ArrayList<Difference>() ;
				
			    	// convert line to words list 
				 ArrayList<String> originalTextWords=convertStringToWords(originalTextLines.get(i));
				 ArrayList<String> changedTextWords=convertStringToWords(changedTextLines.get(i));
				 
				 checkIfCountLinesEquals(originalTextWords,changedTextWords);
					// check words differences 
					for(int j=0;j<originalTextWords.size();j++){
						
						  System.out.println("original : -"+originalTextWords.get(j)+"- changed : -"+changedTextWords.get(j)+"-");
						
					      if(originalTextWords.get(j).equalsIgnoreCase(changedTextWords.get(j))) {
						       
						       originalWordsDifferences.add(new Difference(DiffType.EQUAL,originalTextWords.get(j)));
							   changedWordsDifferences.add(new Difference(DiffType.EQUAL,originalTextWords.get(j)));
							   
					      }
					      
						  else {
							  
							   originalWordsDifferences.add(new Difference(DiffType.REMOVAL,originalTextWords.get(j)));
							   changedWordsDifferences.add(new Difference(DiffType.ADDITION,changedTextWords.get(j)));
						  }
					    
					 }
					
					finalDiffs.getOriginalTextDiffs().add(new LineDifference(i,IsLineDiff.YES,originalWordsDifferences));
					finalDiffs.getChangedTextDiffs().add(new LineDifference(i,IsLineDiff.YES,changedWordsDifferences));
				}
		}
	}
	
	private ArrayList<String>  convertStringToWords(String text) {
		return new ArrayList<String>(Arrays.asList(text.split(" ")));
	}
	
	private ArrayList<String>  convertStringToLines(String text) {
		return new ArrayList<String>(Arrays.asList(text.split(System.getProperty("line.separator"))));	
	}
	
	private void checkIfCountLinesEquals(ArrayList<String> originalTextLines,ArrayList<String> changedTextLines)
	{
		if(originalTextLines.size()!=changedTextLines.size())
			if(originalTextLines.size()>changedTextLines.size())
			  addlines(changedTextLines,originalTextLines.size()-changedTextLines.size());
			else
			 addlines(originalTextLines,changedTextLines.size()-originalTextLines.size());
    }
	
	public void addlines(ArrayList<String> lines,int addednumber) {
		for(int i=0;i<addednumber;i++)
			lines.add("");
	}
	
	
	
	
	
	
}
