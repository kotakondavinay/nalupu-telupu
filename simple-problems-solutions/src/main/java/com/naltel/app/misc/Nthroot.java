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
public class Nthroot {
    public static double root(double a, int b) {
        if(b==1)return a;
        double returnVal = a;
        double diff = Math.abs(a-power(returnVal, b));
            
        while(diff > 0.0001) {
            if(a < power(returnVal, b)) {
                returnVal = returnVal/2.0;
            }
            else {
                returnVal = returnVal*1.5;
            }
            diff = Math.abs(a-power(returnVal, b));
        }
        
        return returnVal;
    }
    
    public static double power(double a, int b){
        if(a == 0.0) return 0;
        if(b == 0) return 1;
        if(b%2 == 0) {
            double returnVal = power(a, b/2);
            return returnVal*returnVal;
        } else {
            double returnVal = power(a, (b-1)/2);
            return returnVal*returnVal*a;
        }
    }
    
}
