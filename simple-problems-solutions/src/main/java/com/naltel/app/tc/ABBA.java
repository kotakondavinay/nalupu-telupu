package com.naltel.app.tc;

public class ABBA {
    public static String canObtain(String source, String destination) {
        boolean isReversed = false; int start = 0, end = destination.length() -1;
        if(source.length()  > destination.length()) return "Impossible";
        while(end - start > source.length()-1) {
            if(!isReversed) {
                if(destination.charAt(end) == 'B')
                    isReversed = true;
                end--;
            } else {
                if(destination.charAt(start) == 'B')
                    isReversed = false;
                start++;
            }
        }
        int j = 0;
        if(!isReversed) {
            for(int i = start; i <= end; i++) {
                if(destination.charAt(i) != source.charAt(j++))
                    return "Impossible";
            }
        } else {
            for (int i = end; i >= start; i--) {
                if(destination.charAt(i) != source.charAt(j++))
                    return "Impossible";
            }
        }
        return "Possible";
    }

    public static boolean canObtainDivHelper(String s, String d, int start, int end, boolean isReversed) {
        if(end-start == s.length() -1 ) {
            int j = 0;
            if(!isReversed) {
                for(int i = start; i <= end; i++)
                    if(s.charAt(j++) != d.charAt(i)) return false;
                return true;
            } else {
                for(int i = end; i >= start; i--)
                    if(s.charAt(j++) != d.charAt(i)) return false;
                return true;
            }
        }
        if(!isReversed) {
            boolean isPossible = false;
            if(d.charAt(end) == 'A')
                isPossible = canObtainDivHelper(s, d, start, end-1, false);
            if(isPossible) return true;
            if(d.charAt(start) == 'B')
                isPossible = canObtainDivHelper(s, d, start+1, end, true);
            return isPossible;
        } else {
            boolean isPossible = false;
            if(d.charAt(start) == 'A')
                isPossible = canObtainDivHelper(s, d, start+1, end, true);
            if(isPossible) return true;
            if(d.charAt(end) == 'B')
                isPossible = canObtainDivHelper(s, d, start, end-1, false);
            return isPossible;
        }
    }
    public static String canObtainDiv1(String source, String destination) {
        boolean isReversed = false; int start = 0, end = destination.length() -1;
        if(source.length()  > destination.length()) return "Impossible";
        if(canObtainDivHelper(source,destination, 0, destination.length()-1, false))
            return "Possible";
        else
            return "Impossible";
    }

    public static void main(String[] args) {
        String s = "BBBBABABBBBBBA";
        String t = "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA";
        System.out.println(canObtain(s,t));

        String s1 = "AAABBAABB";
        String t1 = "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB";
        System.out.println(canObtainDiv1(s1,t1));

        String s2 = "AAABAAABB";
        String t2 = "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB";
        System.out.println(canObtainDiv1(s2,t2));
    }
}
