/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naltel.app.misc;

/**
 *
 * @author vinaykk
 */
public class Palindrome {
 public static boolean isPalindrome(int x) {
     if(x<0) return false;
        int temp2=1,temp=x;
        while(temp/10>0){
            temp = temp/10;
            temp2 = temp2*10;
        }
        temp=x;
        while(temp>0){
            if(temp%10 != (x/temp2) % 10) return false;
            temp = temp/10;
            temp2 = temp2/10;
        }
        return true;   
    }   
}
