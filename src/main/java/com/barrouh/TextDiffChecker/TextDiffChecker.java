package com.barrouh.TextDiffChecker;

import java.util.List;

import com.barrouh.TextDiffChecker.beans.DiffType;
import com.barrouh.TextDiffChecker.beans.Difference;


public class TextDiffChecker {

	public String originalText ="test \r\n" + "test2" , changedText ="test2";
	
	public List<Difference> differences ;
	
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

	public List<Difference> getDifferences() {
		return differences;
	}

	public void findDifferences(){
		// convert tow string to string lines 
		String[] originalTextLines= convertStringToLines(originalText);
		String[] changedTextLines =convertStringToLines(changedText);
		// check differences 
		for(int i=0;i<originalTextLines.length;i++)
		{
			if(!originalTextLines[i].equalsIgnoreCase(changedTextLines[i]))
			{
				String[] originalTextWord=convertStringToWords(originalTextLines[i]);
				String[] changedTextWord=convertStringToWords(changedTextLines[i]);
				
				for(int j=0;j<originalTextWord.length;j++)
				{
					 if(!originalTextWord[j].equalsIgnoreCase(changedTextWord[j]))
						
						 differences.add(new Difference(i+1, DiffType.ADDITION,""));
					
					 else
						 
						 differences.add(new Difference(i+1, DiffType.SAME,""));
				
				}
			}
			
			else
			{
				String[] originalTextWord=convertStringToWords(originalTextLines[i]);
				String[] changedTextWord=convertStringToWords(changedTextLines[i]);
				
				for(int j=0;j<originalTextWord.length;j++)
				{
						 differences.add(new Difference(i+1, DiffType.SAME,""));
				}
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
