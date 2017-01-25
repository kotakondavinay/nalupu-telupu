package com.naltel.app.sorting;

public class MergeSort {
	private int[] numbers;
	  private int[] tempNumbers;

	  private int length;

	  public void sort(int[] values) {
	    this.numbers = values;
	    length = values.length;
	    this.tempNumbers = new int[length];
	    mergesort(0, length - 1);
	  }

	  private void mergesort(int low, int high) {
	    
	    if (low < high) {
	      int middle = low + (high - low) / 2;
	      mergesort(low, middle);
	      mergesort(middle + 1, high);
	      merge(low, middle, high);
	    }
	  }

	  private void merge(int low, int middle, int high) {

	    for (int i = low; i <= high; i++) {
	      tempNumbers[i] = numbers[i];
	    }

	    int i = low;
	    int j = middle + 1;
	    int k = low;
	   
	    while (i <= middle && j <= high) {
	      if (tempNumbers[i] <= tempNumbers[j]) {
	        numbers[k] = tempNumbers[i];
	        i++;
	      } else {
	        numbers[k] = tempNumbers[j];
	        j++;
	      }
	      k++;
	    }
	    while (i <= middle) {
	      numbers[k] = tempNumbers[i];
	      k++;
	      i++;
	    }
	  }
}
