package com.barrouh.TextDiffChecker;

import java.util.ArrayList;


import com.barrouh.TextDiffChecker.beans.DiffType;
import com.barrouh.TextDiffChecker.beans.Difference;
import com.barrouh.TextDiffChecker.beans.FinalDifferences;
import com.barrouh.TextDiffChecker.beans.IsLineDiff;
import com.barrouh.TextDiffChecker.beans.LineDifference;


public class TextDiffChecker {

	private String originalText ="holla \r\n" + "good\r\n" + "test" , 
			       changedText  ="hi\r\n"    + "fine\r\n" + "test" ;
	
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
		String[] originalTextLines= convertStringToLines(originalText);
		String[] changedTextLines =convertStringToLines(changedText);
		// check lines differences 
		for(int i=0;i<originalTextLines.length;i++){
			
			if(originalTextLines[i].equalsIgnoreCase(changedTextLines[i])){
				// add the same lines to final diffs object 
				finalDiffs.getOriginalTextDiffs().add(new LineDifference(i,IsLineDiff.NO));
				finalDiffs.getChangedTextDiffs().add(new LineDifference(i,IsLineDiff.NO));
			   }   
			else{
				
				 final ArrayList<Difference> originalWordsDifferences = new ArrayList<Difference>() ;
				 final ArrayList<Difference> changedWordsDifferences = new ArrayList<Difference>() ;
					
			    	// convert line to words list 
					String[] originalTextWords=convertStringToWords(originalTextLines[i]);
					String[] changedTextWords=convertStringToWords(changedTextLines[i]);
					
					// check words differences 
					for(int j=0;j<originalTextWords.length;j++){
						
						  System.out.println("original : -"+originalTextWords[j]+"- changed : -"+changedTextWords[j]+"-");
						
					      if(originalTextWords[j].equalsIgnoreCase(changedTextWords[j])) {
						       
						       originalWordsDifferences.add(new Difference(DiffType.SAME,originalTextWords[j]));
							   changedWordsDifferences.add(new Difference(DiffType.SAME,originalTextWords[j]));
							   
					      }
					      
						  else {
							  
							   originalWordsDifferences.add(new Difference(DiffType.REMOVAL,originalTextWords[j]));
							   changedWordsDifferences.add(new Difference(DiffType.ADDITION,changedTextWords[j]));
						  }
					    
					 }
					
					finalDiffs.getOriginalTextDiffs().add(new LineDifference(i,IsLineDiff.YES,originalWordsDifferences));
					finalDiffs.getChangedTextDiffs().add(new LineDifference(i,IsLineDiff.YES,changedWordsDifferences));
				}
		}

	}
	
	private String[] convertStringToWords(String text) {
		return text.split(" ");
	}
	
	private String[] convertStringToLines(String text) {
		return text.split(System.getProperty("line.separator"));
	}
	
	
	
	
	
	
	
	
	
}
