package com.barrouh.textdiffchecker.Utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.barrouh.textdiffchecker.TextDiffChecker;
import com.barrouh.textdiffchecker.beans.LineDifference;
import com.barrouh.textdiffchecker.utils.TextDiffCheckerUtils;

public class TextDiffCheckerUtilsTest {
	
	TextDiffChecker textDiffChecker;

	TextDiffCheckerUtils utils = new TextDiffCheckerUtils();
	
	static final Logger LOGGER = LogManager.getLogger(TextDiffCheckerUtilsTest.class);

	Properties prop= new Properties();
	
	public void loadProperties() {
		try {
			prop.load(utils.getPropertiesFile("HtmlElements.properties"));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public String readFile(String fileName) throws IOException {
		return utils.readFromFile("src/test/resources/"+fileName);
	}

	@Test
	public void addHtmlCahrs() {
		utils.setHtmlCahrs(new HashMap<>());
		utils.addHtmlCahrs();
		assertNotEquals(0,utils.getHtmlCahrs().size());
	}
	
	@Test
	public void generateSpecialChar() {
		 assertNotNull(utils.generateSpecialChar());
	}
	
	@Test
	public void getPropertiesFile() {
		assertTrue(prop.isEmpty());
		loadProperties();
		assertFalse(prop.isEmpty());
	}
	
	@Test
	public void getHtmlElementKey() {
		loadProperties();
		assertFalse(prop.isEmpty());
		assertEquals("<tr><th>Original Text</th><th>Changed Text</th></tr>",utils.getHtmlElement("tableTitle"));
	}
	
	@Test
	public void getHtmlElementkeyValue() {
		loadProperties();
		assertFalse(prop.isEmpty());
		assertEquals("<style>Value1</style>",utils.getHtmlElement("styleTag","Value1"));
	}
	
	@Test
	public void getHtmlElementkeyValues() {
		loadProperties();
		assertFalse(prop.isEmpty());
		Object[] values = { "Value1","Value2","Value3" };
		assertEquals("<tr class=diff-row><td class=diff-line-removal><span class=line-number>Value1</span><span>Value2</span></td><td class=diff-line-addition><span class=line-number>Value1</span><span>Value3</span></td>",utils.getHtmlElement("diffRowTrRemoval",values));
	}
	
	@Test
	public void readFromFile() throws IOException {
		assertNotNull(readFile("Changed.txt"));
		assertTrue(readFile("Original.txt").startsWith("test"));
	}
	
	@Test
	public void convertToHtml() throws IOException {
		textDiffChecker = new TextDiffChecker(("Original.txt"),readFile("Changed.txt"));
		Map<LineDifference, LineDifference> finalDiffs = textDiffChecker.getFinalDifferences();
		assertNotNull(finalDiffs);
		assertFalse(finalDiffs.isEmpty());
	}
}
