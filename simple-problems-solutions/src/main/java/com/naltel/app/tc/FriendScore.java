package com.naltel.app.tc;

public class FriendScore {
    public int highestScore(String[] friends) {
        int ans = 0;
        for( int i = 0; i < friends.length; i++) {
            int count = 0;
            for( int j = 0; j < friends[i].length(); j++) {
                if(i == j) continue;
                if(friends[i].charAt(j) == 'Y') count++;
                else {
                    for(int k = 0; k < friends.length; k++) {
                        if( k == i || k == j) continue;
                        if(friends[i].charAt(k) == 'Y' && friends[k].charAt(j) == 'Y') {
                            count++;
                        }
                    }
                }
            }
            ans = Math.max(count, ans);
        }
        return ans;
    }

    public static void main(String[] args) {

        FriendScore fs = new FriendScore();

        String[] f1 = {"NNN",
                "NNN",
                "NNN"};
        System.out.println(fs.highestScore(f1));

        String[] f2 = {"NYY",
                "YNY",
                "YYN"};
        System.out.println(fs.highestScore(f2));

        String[] f3 = {"NYNNN",
                "YNYNN",
                "NYNYN",
                "NNYNY",
                "NNNYN"};
        System.out.println(fs.highestScore(f3));

        String[] f4 = {"NNNNYNNNNN",
                "NNNNYNYYNN",
                "NNNYYYNNNN",
                "NNYNNNNNNN",
                "YYYNNNNNNY",
                "NNYNNNNNYN",
                "NYNNNNNYNN",
                "NYNNNNYNNN",
                "NNNNNYNNNN",
                "NNNNYNNNNN"};
        System.out.println(fs.highestScore(f4));

        String[] f5 = {"NNNNNNNNNNNNNNY",
                "NNNNNNNNNNNNNNN",
                "NNNNNNNYNNNNNNN",
                "NNNNNNNYNNNNNNY",
                "NNNNNNNNNNNNNNY",
                "NNNNNNNNYNNNNNN",
                "NNNNNNNNNNNNNNN",
                "NNYYNNNNNNNNNNN",
                "NNNNNYNNNNNYNNN",
                "NNNNNNNNNNNNNNY",
                "NNNNNNNNNNNNNNN",
                "NNNNNNNNYNNNNNN",
                "NNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNN",
                "YNNYYNNNNYNNNNN"};
        System.out.println(fs.highestScore(f5));
    }
}
