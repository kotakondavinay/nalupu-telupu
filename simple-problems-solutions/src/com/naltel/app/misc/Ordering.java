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
public class Ordering {
    public static boolean getOrdering(String in, String order) {
     Map<Character, Integer> pos = new HashMap<Character, Integer>();

    for(int i = 0; i < in.length(); i++){
        pos.put(in.charAt(i) , i);
    }

    for(int i = 1; i < order.length(); i++){
        if(pos.get(order.charAt(i) - pos.get(order.charAt(i-1))) < 0){
            return false;
       // }
    }
    
    }
    return true;
}
}
