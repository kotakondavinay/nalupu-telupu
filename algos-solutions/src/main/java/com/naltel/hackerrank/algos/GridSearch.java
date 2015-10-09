package com.naltel.hackerrank.algos;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GridSearch {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String test = in.nextLine();
		int testcases = Integer.parseInt(test);
		while( testcases > 0 ) {
			String rowscols = in.nextLine();
			int rows = Integer.parseInt(rowscols.split(" ")[0]);
			int cols = Integer.parseInt(rowscols.split(" ")[1]);
			StringBuilder hTextSb = new StringBuilder();
			StringBuilder vTextSb =  new StringBuilder();
			for(int i = 0; i< rows; i++) {
				String inLine = in.nextLine();
				hTextSb.append(inLine);
			}
			String hText = hTextSb.toString();
			for(int i=0; i < cols; i++) {
				for(int j=0;j < rows;j++) {
					vTextSb.append(hText.charAt(i+cols*j));
				}
			}
			String vText = vTextSb.toString();
			String patternRowsCols = in.nextLine();
			int patternRows = Integer.parseInt(patternRowsCols.split(" ")[0]);
			int patternCols = Integer.parseInt(patternRowsCols.split(" ")[1]);

        	StringBuilder vPatternSb = new StringBuilder();
        	StringBuilder patternSb = new StringBuilder();
        	StringBuilder hPatternSb = new StringBuilder();
        	hPatternSb.append("(.*)");
			int diff = cols - patternCols;
			for(int i = 0; i < patternRows; i++) {
				String inLine = in.nextLine();
				hPatternSb.append(inLine);
				patternSb.append(inLine);
				if(i != patternRows -1) {
					for(int j = 0; j < diff ; j++) {
						hPatternSb.append("\\d");
					}	
				}
			}
			hPatternSb.append("(.*)");
			String pattern = patternSb.toString();
			String hPattern = hPatternSb.toString();
			diff = rows - patternRows;
			vPatternSb.append("(.*)");
			for(int i=0; i < patternCols; i++) {
				for(int j=0;j < patternRows;j++) {
					vPatternSb.append(pattern.charAt(i+patternCols*j));
				}
				if(i != patternCols - 1) {
					for(int j = 0; j < diff ; j++) {
						vPatternSb.append("\\d");
					}
				}
			}
			vPatternSb.append("(.*)");
			String vPattern = vPatternSb.toString();
			Pattern vP = Pattern.compile(vPattern);
			Pattern hP = Pattern.compile(hPattern);
			Matcher vMatcher =  vP.matcher(vText);
			Matcher hMatcher = hP.matcher(hText);
			if(vMatcher.matches() && hMatcher.matches()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			testcases = testcases - 1;
		}
	}
}