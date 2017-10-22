package com.naltel.app.tc;

public class TheAirTripDivTwo {
    public int find(int[] flights, int fuel) {
        int i =0,consumedFuel = 0;
        for(; i < flights.length; i++) {
            consumedFuel += flights[i];
            if(consumedFuel > fuel) break;
        }
        return i;
    }
    public static void main(String[] args) {
        TheAirTripDivTwo at = new TheAirTripDivTwo();

        int[] f1 = {1, 2, 3, 4, 5, 6, 7};
        int fuel1 = 10;
        System.out.println(at.find(f1, fuel1));

        int[] f2 = {7, 6, 5, 4, 3, 2, 1};
        int fuel2 = 10;
        System.out.println(at.find(f2, fuel2));

        int[] f3 = {1};
        int fuel3 = 1000;
        System.out.println(at.find(f3, fuel3));


        int[] f4 = {8, 7, 7, 1, 5, 7, 9};
        int fuel4 = 21;
        System.out.println(at.find(f4, fuel4));
    }
}
