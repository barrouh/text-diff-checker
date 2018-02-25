package com.barrouh.TextDiffChecker.beans;

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


	public String readFromFile(String path) throws IOException{
	
		String line ;
		StringBuilder sb = new StringBuilder("");
	    FileReader fileReader =  new FileReader(path);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    while((line = bufferedReader.readLine()) != null) {
	    sb.append(line+"\r\n");
	    }   
	    bufferedReader.close(); 
		return sb.toString();
	}
	
	public String convertToHtml(FinalDifferences finalDiffs)
	{
		return ToHtml(finalDiffs);
	}
	
	public void convertToHtmlFile(FinalDifferences finalDiffs,String path) throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter(path+"/TowFilesDiffrencesResult.html", "UTF-8");
		writer.println(ToHtml(finalDiffs));
		writer.close();
	}
	
	private String ToHtml(FinalDifferences finalDiffs)
	{
		String Htmltable="";
		// add css style to the table 
		 try {Htmltable+="<style>\n"+readFromFile("src/resources/style.css")+"\n</style>\n";}
		 catch (IOException e) {e.printStackTrace();}
		 
		Htmltable+="<table class='table-style'>\n";
		
		// add table title 
		Htmltable+="\t<tr>\n\t\t<th>Original Text</th>\n\t\t<th>Changed Text</th>\n\t</tr>\n";
		 
		for(int i=0;i<finalDiffs.getOriginalTextDiffs().size();i++){
			
			//if the lines is the same just print it ,
			if(!finalDiffs.getOriginalTextDiffs().get(i).getIsDiff().LineDiff())
			Htmltable+="\t<tr class='diff-row'>\n"  
					  +"\t\t<td  class='diff-line-equal'><span class='line-number'>"+(i+1)+" . </span><span>"+finalDiffs.getOriginalTextDiffs().get(i).getLineValue()+"</span></td>\n"
					  +"\t\t<td  class='diff-line-equal'><span class='line-number'>"+(i+1)+" . </span><span>"+finalDiffs.getChangedTextDiffs().get(i).getLineValue()+"</span></td>\n"
					  +"\t</tr>\n";
			else
			{
				String originaltext="",changedtext="";
				// for original text 
					ArrayList<Difference> originalTextDifferences=	finalDiffs.getOriginalTextDiffs().get(i).getDifferences();
					for(int j=0;j<originalTextDifferences.size();j++) {
						if(originalTextDifferences.get(j).getType().getDiffType().equals("Removal"))
						 originaltext+="<span  class='diff-text-removal'>"+originalTextDifferences.get(j).getDifference()+"</span> ";
						else if(originalTextDifferences.get(j).getType().getDiffType().equals("Addition"))
							     originaltext+="<span  class='diff-text-addition'>"+originalTextDifferences.get(j).getDifference()+"</span> ";
							 else
						    	 originaltext+="<span  class='diff-text-equal'>"+originalTextDifferences.get(j).getDifference()+"</span> ";
					 }
					
					// for changed text 
					ArrayList<Difference> changedTextDifferences=	finalDiffs.getChangedTextDiffs().get(i).getDifferences();
					for(int j=0;j<changedTextDifferences.size();j++) {
						if(changedTextDifferences.get(j).getType().getDiffType().equals("Removal"))
							changedtext+="<span  class='diff-text-removal'>"+changedTextDifferences.get(j).getDifference()+"</span> ";
						else if(changedTextDifferences.get(j).getType().getDiffType().equals("Addition"))
							     changedtext+="<span  class='diff-text-addition'>"+changedTextDifferences.get(j).getDifference()+"</span> ";
							 else
								 changedtext+="<span  class='diff-text-equal'>"+changedTextDifferences.get(j).getDifference()+"</span> ";
					 }
					
					
					Htmltable+="\t<tr class='diff-row'>\n"  
							  +"\t\t<td  class='diff-line-removal'><span class='line-number'>"+(i+1)+" . </span><span>"+originaltext+"</span></td>\n"
							  +"\t\t<td  class='diff-line-addition'><span class='line-number'>"+(i+1)+" . </span><span>"+changedtext+"</span></td>\n"
							  +"\t</tr>\n";
			}
		}
		
		Htmltable+="\n</table>";
		return Htmltable;
	}
	
	
	
	
	
	
	
	
	
}
