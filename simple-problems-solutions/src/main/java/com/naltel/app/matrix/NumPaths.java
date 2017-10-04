package com.naltel.app.matrix;

public class NumPaths {
    public static int numPathUtil(int[][] mat, int r, int c, int k) {
            if(r >= mat.length || c >= mat[0].length || k < 0) return 0;
            if( r == mat.length-1 && c == mat[0].length-1) {
                if(mat[r][c] == k) return 1;
                else return 0;
            }
            return numPathUtil(mat,r+1, c, k-mat[r][c]) + numPathUtil(mat, r, c+1, k-mat[r][c]);
    }
    public static int numPath(int[][] mat, int k) {
        return numPathUtil(mat, 0, 0, k);
    }
    public static void main(String[] args) {
        int k = 12;
        int mat[][] = { {1, 2, 3},
            {4, 6, 5},
            {3, 2, 1}
        };
        System.out.println(numPath(mat, k));
    }
}
