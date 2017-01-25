/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naltel.app.misc;

import java.util.*;
import java.math.*;
/**
 * 
 *
 * @author vinaykk
 */
public class MatrixPalindromePaths {
    
    public static int getPalindromePaths(char[][] matrix, int R, int C) {
        Map<Cell, Integer> map = new HashMap<Cell, Integer>();
        int returnVal = getPalindromeHelper(R, C, matrix, 0, 0, R-1, C-1, map);
        return returnVal;
    }
    
    public static int getPalindromeHelper(int R, int C, char[][] input, int rs, int cs, int re, int ce, Map<Cell, Integer> map) {
        
        if(rs<0 || rs >= R || cs < 0 || cs >=C) return 0;
        if(re<0 || re < rs || ce < 0 || ce < cs) return 0;
        if(input[rs][cs] != input[re][ce]) {
            return 0;
        }
        
        if(Math.abs((rs-re) + cs-ce) <= 1) return 1;
        if(map.get(new Cell(rs,cs,re,ce)) != null) {
            return map.get(new Cell(rs,cs,re,ce));
        }
        int returnVal = 0;
        returnVal += getPalindromeHelper(R,C,input,rs+1,cs,re-1,ce,map);
        returnVal += getPalindromeHelper(R,C,input,rs,cs+1,re,ce-1,map);
        returnVal += getPalindromeHelper(R,C,input,rs+1,cs,re,ce-1,map);
        returnVal += getPalindromeHelper(R,C,input,rs,cs+1,re-1,ce,map);
        map.put(new Cell(rs,cs,re,ce), returnVal);
        return returnVal;        
    }
    
}

class Cell{
    int rs, cs , re, ce;
    
    Cell(int rs,int cs, int re, int ce) {
        this.rs = rs;
        this.cs = cs;
        this.re = re;
        this.ce = ce;
    }
    
   @Override 
   public boolean equals(Object o) {
       final Cell obj = (Cell)o;
     if(this.rs == obj.rs || this.cs == obj.cs || this.re == obj.ce || this.ce == obj.ce){
         return true;
     }  
     return false;
   } 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.rs;
        hash = 67 * hash + this.cs;
        hash = 67 * hash + this.re;
        hash = 67 * hash + this.ce;
        return hash;
    }
   
}