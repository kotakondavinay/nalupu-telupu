package com.naltel.spoj;

import java.io.*;
import java.util.*;

public class Activ {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int offers = in.nextInt();
		while(offers != -1){
			long[][] clubOffers = new long[offers][2];
			for(int i=0;i<offers;i++) {
				long startTime = in.nextLong();
				long endTime = in.nextLong();
				clubOffers[i][0]=startTime;
				clubOffers[i][1]=endTime;
			}
			Arrays.sort(clubOffers, new Comparator<long[]>() {
				public int compare(long[]a,long[]b){
                    return (int) (a[0]-b[0]);
                }
			});
			int[] counts = new int[offers+1];
			counts[offers]=0;
			for(int i=offers-1;i>=0;i--) {
				int index = getindex(clubOffers, clubOffers[i][1]);
				if(index == -1) {
					counts[i]=(counts[i+1]+1);
				} else {
					counts[i]=counts[i+1]+1+counts[index];
				}
			}
			System.out.println(counts[0]);
		}
	}
	
	public static int getindex(long[][] a, long x) {
		for(int i=0;i<a.length;i++) {
			if(a[i][0]>=x) {
				return i;
			}
		}
		return -1;
	}
}