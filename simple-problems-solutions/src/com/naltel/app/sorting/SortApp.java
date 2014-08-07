package com.naltel.app.sorting;

import java.util.Random;

public class SortApp {
	private final static int SIZE = 100;
	private final static int MAX = 100;
	public static void main(String[] args) {
		int[] numbers = new int[SIZE];
	    Random generator = new Random();
	    for (int i = 0; i < numbers.length; i++) {
	      numbers[i] = generator.nextInt(MAX);
	    }
	    
	    /**
	     * Run Merge Sort.
	     */
	    int[] numbersM = numbers;
	    MergeSort mergeSorter = new MergeSort();
	    mergeSorter.sort(numbersM);
	    for (int i = 0; i < numbersM.length - 1; i++) {
	      if (numbersM[i] > numbersM[i + 1]) {
	       System.out.println("Not Sorted");
	      }
	    }
	    for (int i = 0; i < numbersM.length - 1; i++) {
		       System.out.print(numbers[i]+" ");
		}
	    System.out.println();
	    System.out.println("Merge sort Completed");
	    /**
	     * Run Quick Sort.
	     */
	    
	    int[] numbersQ = numbers;
	    QuickSort quickSorter = new QuickSort();
	    quickSorter.sort(numbersQ);
	    for (int i = 0; i < numbersQ.length - 1; i++) {
	      if (numbersQ[i] > numbersQ[i + 1]) {
	       System.out.println("Not Sorted");
	      }
	    }
	    for (int i = 0; i < numbersQ.length - 1; i++) {
		       System.out.print(numbers[i]+" ");
		}
	    System.out.println();
	    System.out.println("Quick Sort Completed");

	}

}
