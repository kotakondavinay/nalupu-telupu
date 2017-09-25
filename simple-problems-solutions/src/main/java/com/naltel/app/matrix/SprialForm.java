package com.naltel.app.matrix;

public class SprialForm {

    public static void spiralPrint(int a[][], int k, int l, int m, int n, int direction) {
        if( k>=m || l>= n) return;
        if(direction == 0) {
            for(int i = l; i < n; i++)
                System.out.print(a[k][i]+" ");
            spiralPrint(a, k+1, l, m, n, 1);
        }
        if(direction == 1) {
            for(int i = k; i < m; i++)
                System.out.print(a[i][n-1]+" ");
            spiralPrint(a, k, l, m, n-1, 2);
        }
        if(direction == 2) {
            for(int i = n-1; i >= l; i--)
                System.out.print(a[m-1][i]+" ");
            spiralPrint(a, k, l, m-1, n, 3);
        }
        if(direction == 3) {
            for(int i = m-1; i >= k; i--)
                System.out.print(a[i][l]+" ");
            spiralPrint(a, k, l+1, m, n, 0);
        }

    }

    public static void spiralPrint(int a[][])
    {
        spiralPrint(a, 0, 0, a.length, a[0].length, 0);
    }

    public static void main (String[] args)
    {
        int a[][] = { {1,  2,  3,  4},
                {5, 6, 7,  8},
                {9,  10, 11, 12},
                {13, 14, 15, 16}
        };
        spiralPrint(a);
    }
}