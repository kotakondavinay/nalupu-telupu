package com.naltel.app.strings;

import java.util.HashMap;
import java.util.Map;

public class FirstRecurringCharacter {
    public static Character recurringChar(String str) {
        Map<Character, Integer> values = new HashMap<>();
        for(Character s: str.toCharArray()) {
            if(values.containsKey(s)) {
                values.put(s, values.get(s) + 1);
            } else {
                values.put(s, 1);
            }
        }
        for(Character s: str.toCharArray()) {
            if(values.containsKey(s) && values.get(s) > 1) {
                return s;
            }
        }

        return null;
    }
    public static void main(String[] args) {
        String str = "ABCBSSKAS";
        Character s = recurringChar(str);
        if(s != null)
            System.out.println(s);
    }
}
