package com.naltel.app.number;

public class FibonacciNumber {

    public static void product(int a[][], int b[][]) {
        int p = (a[0][0] * b[0][0]) + (a[0][1] * b[1][0]);
        int q = (a[0][0] * b[0][1]) + (a[0][1] * b[1][1]);
        int r = (a[1][0] * b[0][0]) + (a[1][1] * b[1][0]);
        int s = (a[1][0] * b[0][1]) + (a[1][1] * b[1][1]);
        a[0][0] = p;
        a[0][1] = q;
        a[1][0] = r;
        a[1][1] = s;
    }
    public static void power(int a[][], int n) {
        if(n == 0|| n==1) return;
        int base[][] = new int[][]{{1,1}, {1,0}};
        power(a, n/2);
        product(a, a);
        if(n%2 != 0) product(a, base);
    }
    public static int linearFibonacci(int n) {
        if(n==1 || n == 2) return 1;
        int base[][] = new int[][]{{1,1}, {1,0}};
        power(base, n-1);
        return base[0][0];
    }

    public static int expExpFibonacci(int n) {
        if(n==1 || n == 2) return 1;
        return expExpFibonacci(n-1)+ expExpFibonacci(n-2);
    }


    public static int expFibonacci(int n) {
        if(n==1 || n == 2) return 1;
        int f1 = 1, f2 =1;
        for(int i = 3; i <= n; i++) {
            int temp = f1+f2;
            f1 = f2;
            f2 = temp;
        }
        return f2;
    }

    public static void main(String[] args) {
        int n = 8;
        System.out.println(linearFibonacci(n));
        System.out.println(expFibonacci(n));
    }
}
