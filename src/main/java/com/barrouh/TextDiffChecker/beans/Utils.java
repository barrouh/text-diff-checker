package com.barrouh.TextDiffChecker.beans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
		String Htmltable="<table style='width:100%'border='1'><tbody>";
		
		for(int i=0;i>finalDiffs.getOriginalTextDiffs().size();i++){
			
			if(!finalDiffs.getOriginalTextDiffs().get(i).getIsDiff().LineDiff())
			Htmltable+="<tr class='diff-row'>"  
					  +"<td class='diff-line'><span class='line-number'>"+(i+1)+"</span><span class='diff-chunk-equal'>"+finalDiffs.getOriginalTextDiffs().get(i).getLine()+"</span></td>"
					  +"<td class='diff-line'><span class='line-number'>"+(i+1)+"</span><span class='diff-chunk-equal'>"+finalDiffs.getChangedTextDiffs().get(i).getLine()+"</span></td>"
					  +"</tr>";
			else
			Htmltable+="<tr class='diff-row'>"
						  +"<td class='line-number'>"+(i+1)+"</td>"
						  +"<td class='diff-line'><span class='diff-chunk-equal'>"+finalDiffs.getOriginalTextDiffs().get(i).getLine()+"</span></td>"
						  +"</tr>";
	
		}
		
		Htmltable+="<tbody/></table>";
		return "";
	}
	
	
	
	
	
	
	
	
	
}
