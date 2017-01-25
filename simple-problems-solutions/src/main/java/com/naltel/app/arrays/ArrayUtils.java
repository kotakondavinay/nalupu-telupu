package com.naltel.app.arrays;

import java.util.Arrays;

public class ArrayUtils {

	public static boolean getSum(int[] a, int x) {
		
		Arrays.sort(a);
		int i =0 , j = a.length - 1;
		while( i < j) {
			if(a[i]+a[j] == x) {
				return true;
			}
			if(a[i]+a[j] > x ) j--;
			if(a[i]+a[j] < x ) i++;
		}
		return false;
	}
	
	public static void mergeSum(int[] first, int[] second) {
		int j = first.length -1;
		for(int i= first.length -1 ; i >= 0; i--) {
			if(first[i] != -1) {
					first[j]=first[i];
					first[i]=-1;
					j--;
			}
		}
		
		int l=second.length,k = 0, m = 0;
		while( l  < first.length && m < second.length) {
			if(first[l] <= second[m]) {
				first[k] = first[l];
				k++;
				l++;
			} else {
				first[k] = second[m];
				k++;
				m++;
			}
		}
	}
	
	public static int getMaxSum(int[] a) {
		int excl=0, incl=0;
		for(int i =0 ; i < a.length; i++) {
			int temp = excl;
			excl = incl;
			incl = temp + a[i];
		}
		return excl > incl ? excl : incl;
	}
	
	public static void printLeaders(int[] a) {
		int max = Integer.MIN_VALUE;
		for(int i =a.length-1 ; i >= 0; i--) {
			if(a[i] > max) {
				System.out.print(a[i] + " ");
				max = a[i];
			}
		}
	}
	
	public static int binarySearch(int[] a, int x) {
		return binarySearchHelper(a, 0, a.length-1, x);
	}
	
	private static int binarySearchHelper(int[] a, int low, int high, int x) {
		if(high >=low) {
			int mid = low + (high -low)/2;
			
			if( (mid==0 || x > a[mid-1]) && a[mid]==x) return mid;
			else if(x > a[mid]) return binarySearchHelper(a, mid+1, high, x);
			else return binarySearchHelper(a, low, mid-1, x);	
		}
		return -1;
	}
	
	public static void segregateArray(int[] a) {
		int left = 0, right = a.length-1;
		while(left<right){
			if(a[left]==0) {
				left++;
			}
			else if(a[right]==1){
				right--;
			}
			else {
				a[left++]=0;
				a[right--]=1;
			}
		}
	}
	
	public static int maxSmallBigSum(int[] a) {	
		int counter=0, max_diff=0, current_min=0;
		while(counter < a.length) {
			if( a[counter] <  a[current_min]) {
				current_min = counter;
			} else if(a[counter] - a[current_min] > max_diff){
				max_diff = a[counter] - a[current_min];
			}
			counter++;
		}
		return max_diff;
	}
	
	public static void printUnionAndIntersection(int[] a, int[] b) {	
		int firstCounter = 0, secondCounter = 0;
		System.out.println("Printing Intersection: ");
		while(firstCounter < a.length && secondCounter  < b.length) {
			if(a[firstCounter] == b[secondCounter]) {
				System.out.print(a[firstCounter]+ " ");
				firstCounter++;
				secondCounter++;
			}
			else if(a[firstCounter] > b[secondCounter] ) {
				secondCounter++;
			} else {
				firstCounter++;
			}
		}
		System.out.println();
		firstCounter = 0; secondCounter = 0;
		System.out.println("Printing Union: ");
		while(firstCounter < a.length && secondCounter  < b.length) {
			if(a[firstCounter] == b[secondCounter]) {
				System.out.print(a[firstCounter]+ " ");
				firstCounter++;
				secondCounter++;
			}
			else if(a[firstCounter] > b[secondCounter] ) {
				System.out.print(b[secondCounter]+ " ");
				secondCounter++;
			} else {
				System.out.print(a[firstCounter]+ " ");
				firstCounter++;
			}
		}
		while(firstCounter < a.length) {
			System.out.print(a[firstCounter]+ " ");
			firstCounter++;
		}
		while(secondCounter < b.length) {
			System.out.print(b[secondCounter]+ " ");
			secondCounter++;
		}
	}
}
