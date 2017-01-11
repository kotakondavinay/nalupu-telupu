package com.naltel.app.arrays;

public class MyArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//int[] a = new int[]{1,2,5,8};
		//int[] a = new int[]{5, 5, 10, 100, 10, 5};
		//int[] a = new int[]{16, 17, 4, 3, 5, 2};
		//int[] a = new int[]{1, 1, 2, 2, 2, 3, 4, 5, 6, 7, 8, 8, 8};
		//System.out.println(ArrayUtils.getSum(a, 13));
		//int[] first = new int[]{1,-1,2,8,-1,-1,-1};
		//ArrayUtils.mergeSum(first, a);
		//for(int i =0 ; i < first.length; i++) {
		//	System.out.print(first[i]+" ");
		//}
		
		//System.out.println(ArrayUtils.getMaxSum(a));
		
		//ArrayUtils.printLeaders(a);
		//System.out.println(ArrayUtils.binarySearch(a, 10));
		//int[] a = new int[]{0,0,1,1,0,1,1};
		//print(a);
		//ArrayUtils.segregateArray(a);
		//print(a);
		
		//int[] a = new int[]{7,9,2,3,4,5};
		//print(a);
		//System.out.println(ArrayUtils.maxSmallBigSum(a));
		//print(a);
		
		int[] a = new int[]{7,9,2,3,4,5};
		int[] b = new int[]{7,9,2,3,4,6};
		print(a);
		print(b);
		ArrayUtils.printUnionAndIntersection(a, b);
	}
	
	public static void print(int[] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i]+ " ");
		}
		System.out.println();
	}

}
