package com.barrouh.textdiffchecker.beans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class Utils {
	
	/**
	* the empty Line value .
	*/
	private String emptyLine ="emptyLine";
	
	/**
	* the special char to replace "" with it .
	*/
	private String specialChar="-:-:-:-";
	
	public void setSpecialChar(final String specialChar) {
		this.specialChar = specialChar;
	}

	public String getSpecialChar() {
		return specialChar;
	}

	
	/**
	* the default constructor of the Utils class 
	*/
	public Utils() {
	}
	
	/**
	* the param constructor of the Utils class 
	*/
	public Utils(final String specialChar) {
		this.specialChar = specialChar;
	}

	/**
	* this method read the text from the provided file and return it as String 
	*/
	public String readFromFile(final String path) throws IOException{
		final StringBuilder outputString = new StringBuilder("");
	    final FileReader fileReader =  new FileReader(path);
	    final BufferedReader bufferedReader = new BufferedReader(fileReader);
	    final StringBuilder line = new StringBuilder("");
	    while(bufferedReader.ready()){
		    line.append(bufferedReader.readLine());
		    if(line.toString().isEmpty()){
		    	line.append(emptyLine).append("\n");
		    	outputString.append(line.toString());
		    	line.setLength(0);
		    }else{
		    	line.append('\n');
		    	outputString.append(line.toString());
		    	line.setLength(0);
		    }
	    }   
	    bufferedReader.close();
		return outputString.toString().replace(" ", specialChar);
	}
	
	/**
	*  return the  FinalDifferences html string 
	*/
	private String readFromInternalFile(final String path) throws IOException{
			return readFromFile(path).replace(specialChar, " "); 
	}
	
	/**
	*  return the  FinalDifferences html string 
	*/
	public String convertToHtml(final FinalDifferences finalDiffs){
		return toHtml(finalDiffs);
	}
	
	/**
	* this method  return the  FinalDifferences html file 
	*/
	public void convertToHtmlFile(final FinalDifferences finalDiffs, final String path) throws FileNotFoundException, UnsupportedEncodingException{
		final PrintWriter writer = new PrintWriter(path+"/TowFilesDiffrencesResult.html", "UTF-8");
		writer.println(toHtml(finalDiffs));
		writer.close();
	}
	
	/**
	* this method  return the  FinalDifferences html file  
	* with file name as parameter 
	*/
	public void convertToHtmlFile(final FinalDifferences finalDiffs, final String path,final String fileName) throws FileNotFoundException, UnsupportedEncodingException{
		final PrintWriter writer = new PrintWriter(path+"/"+fileName, "UTF-8");
		writer.println(toHtml(finalDiffs));
		writer.close();
	}
	
	private String toHtml(final FinalDifferences finalDiffs){
		final StringBuffer htmltable = new StringBuffer(200);
		final StringBuffer cssStyle = new StringBuffer(200);
		// add css style to the table 
		 try {
			 cssStyle.append("<style>\n");
			 cssStyle.append(readFromInternalFile("src/main/resources/style.css"));
			 cssStyle.append("\n</style>\n");
			 htmltable.append(cssStyle.toString());
		 }
		 catch (IOException e) {
			 e.printStackTrace();
		 }
		 
		// open table tag and add table title 
		htmltable.append("<table class='table-style'>\n\t<tr>\n\t\t<th>Original Text</th>\n\t\t<th>Changed Text</th>\n\t</tr>\n");
		 
		deleteEmptyLineWord(finalDiffs);
		
		for(int i=0;i<finalDiffs.getOriginalTextDiffs().size();i++){
			
			//if the lines is the same just print it 
			if(finalDiffs.getOriginalTextDiffs().get(i).getIsDiff().isLineDiff()) {
				
				final StringBuffer originalText = new StringBuffer(200);
				final StringBuffer changedText = new StringBuffer(200);
				
				// for original text 
					ArrayList<Difference> originalTextDifferences=	(ArrayList<Difference>) finalDiffs.getOriginalTextDiffs().get(i).getDifferencesList();
					for(int j=0;j<originalTextDifferences.size();j++) {
						if(originalTextDifferences.get(j).getType()==DiffType.REMOVAL)
									 originalText
									 .append("<span  class='diff-text-removal'>")
								     .append( originalTextDifferences.get(j).getDifferenceValue())
									 .append( "</span> ");
						else if(originalTextDifferences.get(j).getType()==DiffType.ADDITION)
								     originalText
								     .append("<span  class='diff-text-addition'>")
								     .append(originalTextDifferences.get(j).getDifferenceValue())
								     .append("</span> ");
						else if(originalTextDifferences.get(j).getType()==DiffType.EQUAL)
						    	 	 originalText
						    	 	 .append("<span  class='diff-text-equal'>")
						    		 .append(originalTextDifferences.get(j).getDifferenceValue())
						    		 .append("</span> ");
					 }
					
					// for changed text 
					ArrayList<Difference> changedTextDifferences=	(ArrayList<Difference>) finalDiffs.getChangedTextDiffs().get(i).getDifferencesList();
					for(int j=0;j<changedTextDifferences.size();j++) {
						if(changedTextDifferences.get(j).getType()==DiffType.REMOVAL)
									 changedText
									 .append("<span  class='diff-text-removal'>")
									 .append(changedTextDifferences.get(j).getDifferenceValue())
									 .append("</span> ");
						else if(changedTextDifferences.get(j).getType()==DiffType.ADDITION)
								     changedText
								     .append("<span  class='diff-text-addition'>")
								     .append(changedTextDifferences.get(j).getDifferenceValue())
								     .append("</span> ");
						else if(changedTextDifferences.get(j).getType()==DiffType.EQUAL)
									 changedText
									 .append("<span  class='diff-text-equal'>")
									 .append(changedTextDifferences.get(j).getDifferenceValue())
									 .append("</span> ");
					 }

							 htmltable
							 .append("\t<tr class='diff-row'>\n\t\t<td  class='diff-line-removal'><span class='line-number'>")
					         .append(i+1)
					         .append(" . </span><span>")
					         .append(originalText)
					         .append("</span></td>\n\t\t<td  class='diff-line-addition'><span class='line-number'>")
					         .append(i+1)
					         .append(" . </span><span>")
					         .append(changedText)
					         .append("</span></td>\n\t</tr>\n");
				
			}else{
				
							 final String originalText=finalDiffs.getOriginalTextDiffs().get(i).getLineValue();
							 final String changedText=finalDiffs.getChangedTextDiffs().get(i).getLineValue();
							 final String span=" . </span><span>";
							
							 htmltable
							 .append("\t<tr class='diff-row'>\n\t\t<td  class='diff-line-equal'><span class='line-number'>")
							 .append(i+1)
							 .append(span)
							 .append(originalText)
							 .append("</span></td>\n\t\t<td  class='diff-line-equal'><span class='line-number'>")
							 .append(i+1)
							 .append(span)
							 .append(changedText)
							 .append("</span></td>\n\t</tr>\n");
			}
		}
		
		// close table tag 
		htmltable.append("\n</table>");
		return htmltable.toString().replace(specialChar,"<p class='space_char'> </p>");
	}
		
	private void deleteEmptyLineWord(FinalDifferences finalDiffs){
		//for original text
		for(int i=0;i<finalDiffs.getOriginalTextDiffs().size();i++){
			if(finalDiffs.getOriginalTextDiffs().get(i).getLineValue()!=null) {
				if(finalDiffs.getOriginalTextDiffs().get(i).getLineValue().equals(emptyLine))
					finalDiffs.getOriginalTextDiffs().get(i).setLineValue("");
			}else{
				for(int j=0;j<finalDiffs.getOriginalTextDiffs().get(i).getDifferencesList().size();j++){
					if(finalDiffs.getOriginalTextDiffs().get(i).getDifferencesList().get(j).getDifferenceValue().equals(emptyLine))
						finalDiffs.getOriginalTextDiffs().get(i).getDifferencesList().get(j).setDifferenceValue("");
				}
			}	
		}
		
		// for changed text
		for(int i=0;i<finalDiffs.getChangedTextDiffs().size();i++){
			if(finalDiffs.getChangedTextDiffs().get(i).getLineValue()!=null) {
				if(finalDiffs.getChangedTextDiffs().get(i).getLineValue().equals(emptyLine))
					finalDiffs.getChangedTextDiffs().get(i).setLineValue("");
			}
			else{
				for(int j=0;j<finalDiffs.getChangedTextDiffs().get(i).getDifferencesList().size();j++){
					if(finalDiffs.getChangedTextDiffs().get(i).getDifferencesList().get(j).getDifferenceValue().equals(emptyLine))
						finalDiffs.getChangedTextDiffs().get(i).getDifferencesList().get(j).setDifferenceValue("");
				}
			}	
		
	 }
}

}	
	

