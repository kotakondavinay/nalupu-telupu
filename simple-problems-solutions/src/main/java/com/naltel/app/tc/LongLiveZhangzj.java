package com.naltel.app.tc;

public class LongLiveZhangzj {
    public int donate(String[] speech, String[] words) {
        int ans = 0;
        for( String s: speech) {
            for(String w: words) {
                if (s.equals(w)) {
                    ans +=1;
                    break;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String[] speech1 = {"make", "topcoder", "great", "again"};
        String[] words1 = {"make", "america", "great", "again"};
        LongLiveZhangzj o1 = new LongLiveZhangzj();
        System.out.println(o1.donate(speech1, words1));

        String[] speech2 = {"toads"};
        String[] words2 = {"toad"};
        System.out.println(o1.donate(speech2, words2));

        String[] speech3 = {"a", "a"};
        String[] words3 = {"a"};
        System.out.println(o1.donate(speech3, words3));

        String[] speech4 ={"je", "le", "ai", "deja", "vu", "et", "je", "le", "veux", "encore"};
        String[] words4 = {"i", "am", "having", "deja", "vu", "please", "stop", "the", "encore"};
        System.out.println(o1.donate(speech4, words4));
    }
}
