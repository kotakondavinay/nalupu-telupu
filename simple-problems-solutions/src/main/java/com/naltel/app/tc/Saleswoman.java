package com.naltel.app.tc;

public class Saleswoman {
    public static int minMoves(int[] pos, int[] delta) {
        int lastnegIndex = -1;
        int demand = 0;
        int supply = 0;
        int ans = 0;

        for(int i = 0; i < delta.length; i++) {
            if(delta[i] >= 0) supply += delta[i];
            else demand += delta[i];
            if(supply >= -demand) {
                if(lastnegIndex != -1) {
                    ans += 2 * (pos[i] - pos[lastnegIndex]);
                    lastnegIndex = -1;
                    supply += demand;
                    demand = 0;
                }
            } else {
                if(lastnegIndex == -1) {
                    lastnegIndex = i;
                }
            }
        }
        ans += pos[pos.length-1];
        return ans;
    }

    public static void main(String[] args) {
        int[] a1= {3,14,15,92,101};
        int[] b1= {-3,2,3,-3,1};
        System.out.println(minMoves(a1, b1));
        int[] a2 ={1,2,4,8,16,32,64,128};
        int[] b2 = {-1,-1,-1,-1,1,1,1,1};
        System.out.println(minMoves(a2, b2));
        int[] a3 = {100000};
        int[] b3 =  {0};
        System.out.println(minMoves(a3, b3));
        int[] a4 = {100,200,300,400};
        int[] b4 = {10,-3,-5,2};
        System.out.println(minMoves(a4, b4));
        int[] a5 = {1,2,3,5,8,13,21,34,55,89};
        int[] b5 = {-1,1,-1,1,-1,1,-1,1,-1,1};
        System.out.println(minMoves(a5, b5));
        int[] a6 = {1,2,3,6,10,15,21,28,36,45,55};
        int[] b6 = {-3,-5,10,-2,-6,-7,3,-2,8,5,-1};
        System.out.println(minMoves(a6, b6));
    }
}
