package com.naltel.app.misc;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestClass1 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
         * Read input from stdin and provide input before running
         * Use either of these methods for input
         * */

        //BufferedReader
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String line = br.readLine();
        //int N = Integer.parseInt(line);

        //Scanner
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int M = s.nextInt();
        int[][] mat = new int[N][M];
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
        	for(int j = 0; j < M ; j++) {
        		mat[i][j] = s.nextInt();
        		if(maxVal < mat[i][j]) {
        			maxVal = mat[i][j];
        		}
        	}
        }
        int[][] cumulativeMat = new int[N][M];
       // int maxVal = cumulativeMat[0][0];
        for(int i = 0; i < N; i++) {
        	int val = 0;
        	for(int j = 0; j < M; j++) {
        		val = val ^ mat[i][j];
        		cumulativeMat[i][j] =val;
        		if(maxVal < val) {
        			maxVal = val;
        		}
        	}
        }
        for(int i = 0; i < M; i++) {
        	int val = 0;
        	for(int j = 0; j < N; j++) {
        		val = val ^ mat[j][i];
        		cumulativeMat[j][i] =val;
        		if(maxVal < val) {
        			maxVal = val;
        		}
        	}
        }
        for(int i = 0; i < N; i++) {
        	for(int j = 0 ; j < M; j++) {
        		for(int l = i+1; l < N; l++) {
        			for(int m = j+1; m < M; m++) {
        				int currentMatVal = getVal(cumulativeMat, i,j,l,m);
        				if(maxVal < currentMatVal) {
        					maxVal = currentMatVal;
        				}
        			}
        		}
        	}
        }
        System.out.println(maxVal);

	}
	
	public static int getVal(int[][] cumulativeMat, int i, int j, int l, int m) {
		//int returnVal = cumulativeMat[l][m];
		return cumulativeMat[l][m] ^ cumulativeMat[i][j] ^ cumulativeMat[i][m] ^ cumulativeMat[l][j];
		//return ;
	}
}
