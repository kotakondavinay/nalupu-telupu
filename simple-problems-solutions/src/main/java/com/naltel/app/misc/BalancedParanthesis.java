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
public class BalancedParanthesis {
    public static void getFunc(){
        String input = "*(*(**)*";
        //String input = "(*(*)*)";
        System.out.println(getParanthesisCount(input));
    }
    
    public static long getParanthesisCount(String input) {
        long count = 0;
        boolean isSpecial = false;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '*') {
                 if(i == 0) {
                     System.out.println(input+ " :old");
                    String newInput = '(' + input.substring(i+1);
                    System.out.println(newInput+ " :new");
                    count += getParanthesisCount(newInput);
                    newInput = input.substring(i+1);
                    System.out.println(newInput+ " :new");
                    count += getParanthesisCount(newInput);             
                }
                else if(i != input.length() - 1) {
                    String newInput = input.substring(0, i-1) + '(' + input.substring(i+1);
                    count += getParanthesisCount(newInput);
                    newInput = input.substring(0, i-1) + ')' + input.substring(i+1);
                    count += getParanthesisCount(newInput);
                    newInput = input.substring(0, i-1) + input.substring(i+1);
                    count += getParanthesisCount(newInput);
                } else {
                    String newInput = input.substring(0, i-1) + ')';
                    count += getParanthesisCount(newInput);
                    newInput = input.substring(0, i-1);
                    count += getParanthesisCount(newInput);
                }
                 isSpecial = true;
                break;
            }
        }
        if(!isSpecial) {
            if(isCorrectFormat(input)) {
                System.out.println(input+" length is: "+input.length());
                count += 1;
            }
        }
        return count;
    }
    
    public static boolean isCorrectFormat(String input) {
        int startCount = 0;
        //int endCount = 0;
        if(input.isEmpty()) return false;
        for(int i=0;i< input.length();i++){
            if(input.charAt(i) == '(') {
                startCount += 1;
            }
            if(input.charAt(i) == ')') {
                startCount -= 1;
            }
            if(startCount < 0) return false;
        }
        if(startCount > 0) return false;
        return true;
    }
    
}
