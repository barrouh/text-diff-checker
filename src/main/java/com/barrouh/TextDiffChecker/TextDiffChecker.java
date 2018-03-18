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

	private String originalText,changedText;
	
	private FinalDifferences finalDiffs = new FinalDifferences();
	
	private ArrayList<Difference> originalWordsDifferences;
	
	private ArrayList<Difference> changedWordsDifferences;
	
	public TextDiffChecker() {}

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

	public FinalDifferences getFinalDifferences() {
		// avoid repetition when calling getFinalDifferences tow times from the same object 
		finalDiffs = new FinalDifferences();
		findDifferences();
		return finalDiffs;
	}

	private void findDifferences(){
		// convert input strings to string lines 
		ArrayList<String>  originalTextLines= convertStringToLines(originalText);
		ArrayList<String>  changedTextLines =convertStringToLines(changedText);
		
		// avoid null pointer exception 
		checkIfCountOfLinesOrWordsEquals(originalTextLines,changedTextLines);
		// check lines differences 
		System.out.println("originalTextLines : "+originalTextLines.size());
		System.out.println("changedTextLines : "+changedTextLines.size());
		for(int i=0;i<originalTextLines.size();i++){
			
			/*				
				System.out.println("originalTextLines : "+originalTextLines.get(i));
				System.out.println("changedTextLines : "+changedTextLines.get(i));
							
			if(originalTextLines.get(i).equals("emptyLine") || changedTextLines.get(i).equals("emptyLine") ){
				
				 final ArrayList<Difference> originalWordsDifferences = new ArrayList<Difference>() ;
				 final ArrayList<Difference> changedWordsDifferences = new ArrayList<Difference>() ;
				
				if(originalTextLines.get(i).equals("emptyLine") && changedTextLines.get(i).equals("addedLine")) {
					originalWordsDifferences.add(new Difference(DiffType.REMOVAL,originalTextLines.get(i)));
					changedWordsDifferences.add(new Difference(DiffType.ADDITION,originalTextLines.get(i)));
					
					finalDiffs.getOriginalTextDiffs().add(new LineDifference(i,IsLineDiff.YES,originalWordsDifferences));
					finalDiffs.getChangedTextDiffs().add(new LineDifference(i,IsLineDiff.YES,changedWordsDifferences));
				}
				else if(originalTextLines.get(i).equals("addedLine") && changedTextLines.get(i).equals("emptyLine"))
				{
					originalWordsDifferences.add(new Difference(DiffType.ADDITION,originalTextLines.get(i)));
					changedWordsDifferences.add(new Difference(DiffType.REMOVAL,originalTextLines.get(i)));
					
					finalDiffs.getOriginalTextDiffs().add(new LineDifference(i,IsLineDiff.YES,originalWordsDifferences));
					finalDiffs.getChangedTextDiffs().add(new LineDifference(i,IsLineDiff.YES,changedWordsDifferences));
				}
			}
		    else */ if(originalTextLines.get(i).equalsIgnoreCase(changedTextLines.get(i))){
				// add the same lines to final diffs object 
				finalDiffs.getOriginalTextDiffs().add(new LineDifference(i,IsLineDiff.NO,originalTextLines.get(i)));
				finalDiffs.getChangedTextDiffs().add(new LineDifference(i,IsLineDiff.NO,changedTextLines.get(i)));
			   }
			else{
				
				 originalWordsDifferences = new ArrayList<Difference>() ;
				 changedWordsDifferences = new ArrayList<Difference>() ;
			     // convert line to words list 
				 ArrayList<String> originalTextWords=convertStringToWords(originalTextLines.get(i));
				 ArrayList<String> changedTextWords=convertStringToWords(changedTextLines.get(i));
				 // check if lines count is equal for the tow list , to avoid out of range exception 
				 checkIfCountOfLinesOrWordsEquals(originalTextWords,changedTextWords);
				// check words differences 
				 checkWordsDifferences(originalTextWords,changedTextWords,i);
				}
		}
	}
	
	private void checkWordsDifferences(ArrayList<String> originalTextWords,ArrayList<String> changedTextWords , int index ){
		for(int j=0;j<originalTextWords.size();j++){
		      if(originalTextWords.get(j).equalsIgnoreCase(changedTextWords.get(j))){
		    	  
			       originalWordsDifferences.add(new Difference(DiffType.EQUAL,originalTextWords.get(j)));
				   changedWordsDifferences.add(new Difference(DiffType.EQUAL,originalTextWords.get(j)));   
		      }
			  else{
				  
				   originalWordsDifferences.add(new Difference(DiffType.REMOVAL,originalTextWords.get(j)));
				   changedWordsDifferences.add(new Difference(DiffType.ADDITION,changedTextWords.get(j)));
			  } 
		 }
		finalDiffs.getOriginalTextDiffs().add(new LineDifference(index,IsLineDiff.YES,originalWordsDifferences));
		finalDiffs.getChangedTextDiffs().add(new LineDifference(index,IsLineDiff.YES,changedWordsDifferences));
	}
	
	private ArrayList<String>  convertStringToWords(String text) {
		ArrayList<String> wordsList = new ArrayList<String>(Arrays.asList(text.split(" ")));
		// avoid ignore spaces value
		for(int i=0;i<wordsList.size();i++)
			if(wordsList.get(i).equals(""))
				wordsList.set(i, " ");
		return wordsList;	
	}
	
	private ArrayList<String>  convertStringToLines(String text) {
		return new ArrayList<String>(Arrays.asList(text.split(System.getProperty("line.separator"))));	
	}
	
	private void checkIfCountOfLinesOrWordsEquals(ArrayList<String> originalTextLines,ArrayList<String> changedTextLines)
	{
		if(originalTextLines.size()!=changedTextLines.size())
			if(originalTextLines.size()>changedTextLines.size())
			  addlines(changedTextLines,originalTextLines.size()-changedTextLines.size());
			else
			 addlines(originalTextLines,changedTextLines.size()-originalTextLines.size());
    }
	
	private void addlines(ArrayList<String> lines,int addednumber) {
		for(int i=0;i<addednumber;i++)
			lines.add("");
	}
	
	
	
	
	
	
}
