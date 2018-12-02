package com.barrouh.textdiffchecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.barrouh.textdiffchecker.beans.DiffType;
import com.barrouh.textdiffchecker.beans.Difference;
import com.barrouh.textdiffchecker.beans.IsLineDiff;
import com.barrouh.textdiffchecker.beans.LineDifference;
import com.barrouh.textdiffchecker.utils.MapComparator;

/**
 * @author <a href="mailto:mohamed.barrouh@gmail.com">Mohamed Barrouh</a>
 *
 */
public class TextDiffChecker {

	private String originalText;

	private String changedText;
	
	Map<LineDifference, LineDifference> finalDiffs = new TreeMap<>(new MapComparator());

	private List<Difference> originalWordsDifs;

	private List<Difference> changedWordsDifs;

	public TextDiffChecker() {}

	public TextDiffChecker(final String originalText, final String changedText) {
		this.originalText = originalText;
		this.changedText = changedText;
	}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(final String originalText) {
		this.originalText = originalText;
	}

	public String getChangedText() {
		return changedText;
	}

	public void setChangedText(final String changedText) {
		this.changedText = changedText;
	}

	public Map<LineDifference, LineDifference> getFinalDifferences() {
		findDifferences();
		return finalDiffs;
	}

	private void findDifferences() {
		/* convert input strings to string lines */
		final List<String> originalTextLines = convertStringToLines(originalText);
		final List<String> changedTextLines = convertStringToLines(changedText);
		/* avoid null pointer exception */
		checkIfCountOfLinesOrWordsEquals(originalTextLines, changedTextLines);
		for (int i = 0; i < originalTextLines.size(); i++) {
			if (originalTextLines.get(i).equalsIgnoreCase(changedTextLines.get(i))) {
				/* add the same lines to final diffs object */
				finalDiffs.put(new LineDifference(i, IsLineDiff.NO, originalTextLines.get(i)), new LineDifference(i, IsLineDiff.NO, changedTextLines.get(i)));
			} else {
				originalWordsDifs = new ArrayList<>();
				changedWordsDifs = new ArrayList<>();
				/* convert line to words list */
				final ArrayList<String> originalTextWords = convertStringToWords(originalTextLines.get(i));
				final ArrayList<String> changedTextWords = convertStringToWords(changedTextLines.get(i));
				/* check if lines count is equal for the tow list , to avoid out of range exception */
				checkIfCountOfLinesOrWordsEquals(originalTextWords, changedTextWords);
				/* check words differences */
				checkWordsDifferences(originalTextWords, changedTextWords, i);
			}
		}
	}

	private void checkWordsDifferences(ArrayList<String> originalTextWords, ArrayList<String> changedTextWords,int index) {
		for (int j = 0; j < originalTextWords.size(); j++) {
			if (originalTextWords.get(j).equalsIgnoreCase(changedTextWords.get(j))) {
				originalWordsDifs.add(new Difference(DiffType.EQUAL, originalTextWords.get(j)));
				changedWordsDifs.add(new Difference(DiffType.EQUAL, originalTextWords.get(j)));
			} else {
				originalWordsDifs.add(new Difference(DiffType.REMOVAL, originalTextWords.get(j)));
				changedWordsDifs.add(new Difference(DiffType.ADDITION, changedTextWords.get(j)));
			}
		}
		finalDiffs.put(new LineDifference(index, IsLineDiff.YES, originalWordsDifs),new LineDifference(index, IsLineDiff.YES, changedWordsDifs));
	}

	private ArrayList<String> convertStringToWords(final String text) {
		final ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(text.split(" ")));
		/* avoid ignore spaces value */
		for (int i = 0; i < wordsList.size(); i++) {
			if (wordsList.get(i).equals("")) {
				wordsList.set(i, " ");
			}
		}
		return wordsList;
	}

	private List<String> convertStringToLines(final String text) {
		return new ArrayList<>(Arrays.asList(text.split(System.getProperty("line.separator"))));
	}

	private void checkIfCountOfLinesOrWordsEquals(final List<String> originalTextLines,final List<String> changedTextLines) {
		if (originalTextLines.size() != changedTextLines.size()) {
			if (originalTextLines.size() > changedTextLines.size()) {
				addlines(changedTextLines, originalTextLines.size() - changedTextLines.size());
			} else {
				addlines(originalTextLines, changedTextLines.size() - originalTextLines.size());
			}
		}
	}

	private void addlines(final List<String> lines, final int addednumber) {
		for (int i = 0; i < addednumber; i++) {
			lines.add("");
		}
	}

}
