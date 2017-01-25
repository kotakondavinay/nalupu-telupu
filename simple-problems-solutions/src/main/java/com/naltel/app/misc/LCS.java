/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naltel.app.misc;

import java.util.*;
/**
 *
 * @author vinaykk
 */
public class LCS {
    public static void getLCSLength() {
        Scanner in = new Scanner(System.in);
        String first = in.next();
        String second= in.next();
        int m = first.length(), n = second.length();
        int[][] lcsValues = new int[m+1][n+1];
        for(int i = 0; i < m; i++) {
            if(first.charAt(i) == second.charAt(0)) {
                lcsValues[i][0] = 1;
            } else {
                lcsValues[i][0] = 0;    
            }
        }
        for(int i = 0; i < n; i++) {
            if(first.charAt(0) == second.charAt(i)) {
                lcsValues[0][i] = 1;
            } else {
                lcsValues[0][i] = 0;    
            }
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n;j++) {
                if(first.charAt(i) != second.charAt(j)) {
                   lcsValues[i][j] = (lcsValues[i][j-1] > lcsValues[i-1][j] ? lcsValues[i][j-1] : lcsValues[i-1][j]);
                } else {
                    lcsValues[i][j] = 1+lcsValues[i-1][j-1];
                }    
            }
        }
        System.out.println(lcsValues[m-1][n-1]);
    }
}
