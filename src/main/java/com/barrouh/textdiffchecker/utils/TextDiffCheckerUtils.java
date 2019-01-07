package com.barrouh.textdiffchecker.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barrouh.textdiffchecker.beans.DiffType;
import com.barrouh.textdiffchecker.beans.Difference;
import com.barrouh.textdiffchecker.beans.LineDifference;

/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class TextDiffCheckerUtils {

	static final Logger LOGGER = LogManager.getLogger(TextDiffCheckerUtils.class);

	private static final String EMPTYLINE = "emptyLine";

	private Properties htmlElements;

	private String specialChar;

	private Map<String, String> htmlCahrs = new HashMap<>();

	/**
	 * Add defaults HtmlCahrs
	 */
	public void addHtmlCahrs() {
		htmlCahrs.put("<", "&lt;");
		htmlCahrs.put(">", "&gt;");
	}

	public void addHtmlCahrs(String name, String result) {
		htmlCahrs.put(result, name);
	}

	/**
	 * the special string to replace " " with it . this parameter now will generated
	 * automatically to avoid comparison problems
	 * @return random string
	 */
	public String generateSpecialChar() {
		return Long.toHexString(Double.doubleToLongBits(Math.random()));
	}

	public TextDiffCheckerUtils() {
		try {
			addHtmlCahrs();
			specialChar = generateSpecialChar();
			htmlElements = new Properties();
			htmlElements.load(getPropertiesFile(Constants.HTMLELEMENTSFILENAME));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public TextDiffCheckerUtils(final String specialChar) {
		try {
			addHtmlCahrs();
			this.specialChar = specialChar;
			htmlElements = new Properties();
			htmlElements.load(getPropertiesFile(Constants.HTMLELEMENTSFILENAME));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * get the element from properties file as InputStream
	 * @param fileName or filPath of properties file
	 * @return properties as InputStream
	 */
	public InputStream getPropertiesFile(String fileName) {
		return getClass().getClassLoader().getResourceAsStream(fileName);
	}

	public String getHtmlElement(String key, String value) {
		Object[] values = { value };
		return getHtmlElement(key, values);
	}

	public String getHtmlElement(String key) {
		Object[] values = null;
		return getHtmlElement(key, values);
	}

	public String getHtmlElement(String key, Object[] values) {
		String result = "";
		String msg = htmlElements.getProperty(key);
		if (values != null && values.length > 0 && msg != null) {
			result = MessageFormat.format(msg, values);
		} else if (values == null) {
			result = msg;
		}
		return result;
	}

	/**
	 * This method read the text from the provided file and return it as String
	 * @param path of the file
	 * @return file content as string
	 * @throws IOException if there is a problem while reading the file
	 */
	public String readFromFile(final String path) throws IOException {
		StringBuilder outputString = new StringBuilder("");
		FileReader fileReader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuilder line = new StringBuilder("");
		try {
			while (bufferedReader.ready()) {
				line.append(bufferedReader.readLine());
				if (line.toString().isEmpty()) {
					line.append(EMPTYLINE).append("\n");
					outputString.append(line.toString());
					line.setLength(0);
				} else {
					line.append('\n');
					outputString.append(line.toString());
					line.setLength(0);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			bufferedReader.close();
		}

		if (outputString.toString().contains(specialChar)) {
			this.specialChar = this.generateSpecialChar();
			return outputString.toString().replace(" ", specialChar);
		} else {
			return outputString.toString().replace(" ", specialChar);
		}
	}

	/**
	 * @param finalDiffs Map Original line, Changed line
	 * @return final result as html table
	 */
	public String convertToHtml(final Map<LineDifference, LineDifference> finalDiffs) {
		return toHtml(finalDiffs);
	}

	/**
	 * This method return the FinalDifferences html file 
	 * @param finalDiffs Map Original line, Changed line
	 * @param path of output file contain file name
	 */
	public void convertToHtmlFile(final Map<LineDifference, LineDifference> finalDiffs, final String path) {
		try (PrintWriter writer = new PrintWriter(path + Constants.HTMLRESULTDEFAULTFILENAME, Constants.DEFAULTCHARSET)) {
			writer.println(toHtml(finalDiffs));
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method return the FinalDifferences html file with file name as parameter
	 * @param finalDiffs Map Original line, Changed line
	 * @param path of output file
	 * @param fileName name of output file
	 */
	public void convertToHtmlFile(final Map<LineDifference, LineDifference> finalDiffs, final String path,
			final String fileName) {
		try (PrintWriter writer = new PrintWriter(path + "/" + fileName, Constants.DEFAULTCHARSET)) {
			writer.println(toHtml(finalDiffs));
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private String getFileFromJarFile(String filename) {
		InputStream in = getClass().getResourceAsStream("/" + filename);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		return reader.lines().collect(Collectors.joining());
	}

	private String toHtml(final Map<LineDifference, LineDifference> finalDiffs) {
		final StringBuilder htmltable = new StringBuilder();
		// add css style to the table
		String cssStyle = getHtmlElement(Constants.STYLETAG, getFileFromJarFile(Constants.STYLEFILENAME));

		deleteEmptyLineWord(finalDiffs);
		htmlValidator(finalDiffs);
		int i = 1;

		for (Map.Entry<LineDifference, LineDifference> entry : finalDiffs.entrySet()) {
			// if the lines is the same just print it
			if (entry.getKey().getIsDiff().isLineDiff()) {
				StringBuilder originalText = new StringBuilder();
				StringBuilder changedText = new StringBuilder();

				// for original text
				List<Difference> originalTextDifferences = entry.getKey().getDifferencesList();
				originalText.append(addWordsSpans(originalTextDifferences));

				// for changed text
				List<Difference> changedTextDifferences = entry.getValue().getDifferencesList();
				changedText.append(addWordsSpans(changedTextDifferences));
				
				String[] args = { i + " . ", originalText.toString(), changedText.toString() };
				htmltable.append(getHtmlElement("diffRowTrRemoval", args));
				
			} else {
				String originalText = entry.getKey().getLineValue();
				String changedText = entry.getValue().getLineValue();
				String[] args = { i + " . ", originalText, changedText };
				htmltable.append(getHtmlElement("diffRowTrEqual", args));
			}
			i++;
		}

		// close table tag
		return cssStyle.concat(getHtmlElement("tableTag", getHtmlElement("tableTitle").concat(htmltable.toString()))
				.replace(specialChar, "<p class='space_char'> </p>"));
	}

	private String addWordsSpans(List<Difference> textDifferences) {
		StringBuilder finalString = new StringBuilder();
		for (Difference difference : textDifferences) {
			if (difference.getType() == DiffType.REMOVAL) {
				finalString.append(getHtmlElement(Constants.REMOVALSPAN, difference.getDifferenceValue()));
			} else if (difference.getType() == DiffType.ADDITION) {
				finalString.append(getHtmlElement(Constants.ADDITIONSPAN, difference.getDifferenceValue()));
			} else if (difference.getType() == DiffType.EQUAL) {
				finalString.append(getHtmlElement(Constants.EQUALSPAN, difference.getDifferenceValue()));
			}
		}
		return finalString.toString();
	}

	private String htmlElementValidator(String element) {
		Set<Map.Entry<String, String>> htmlElementsSet = htmlCahrs.entrySet();
		for (Map.Entry<String, String> htmlElement : htmlElementsSet) {
			if (element.contains(htmlElement.getKey())) {
				element = element.replace(htmlElement.getKey(), htmlElement.getValue());
			}
		}
		return element;
	}

	private void htmlValidator(Map<LineDifference, LineDifference> finalDiffs) {
		for (Map.Entry<LineDifference, LineDifference> entry : finalDiffs.entrySet()) {
			/* for original text */
			doValidateHtml(entry.getKey());
			/* for changed text */
			doValidateHtml(entry.getValue());
		}
	}

	private void doValidateHtml(LineDifference lineDifference) {
		if (lineDifference.getLineValue() != null) {
			lineDifference.setLineValue(htmlElementValidator(lineDifference.getLineValue()));
		} else {
			for (Difference difference : lineDifference.getDifferencesList()) {
				difference.setDifferenceValue(htmlElementValidator(difference.getDifferenceValue()));
			}
		}
	}

	private void deleteEmptyLineWord(Map<LineDifference, LineDifference> finalDiffs) {
		for (Map.Entry<LineDifference, LineDifference> entry : finalDiffs.entrySet()) {
			/* for original text */
			replaceEmptyLineWord(entry.getKey(), EMPTYLINE);
			/* for changed text */
			replaceEmptyLineWord(entry.getValue(), EMPTYLINE);
		}
	}

	private void replaceEmptyLineWord(LineDifference lineDifference, String replaceWith) {
		if (lineDifference != null) {
			if (null != lineDifference.getLineValue()) {
				if (lineDifference.getLineValue().equals(replaceWith)) {
					lineDifference.setLineValue("");
				}
			} else if (null != lineDifference.getDifferencesList()) {
				for (Difference difference : lineDifference.getDifferencesList()) {
					if (difference.getDifferenceValue().equals(replaceWith)) {
						difference.setDifferenceValue("");
					}
				}
			}
		}
	}

	public Map<String, String> getHtmlCahrs() {
		return htmlCahrs;
	}

	public void setHtmlCahrs(Map<String, String> htmlCahrs) {
		this.htmlCahrs = htmlCahrs;
	}

}