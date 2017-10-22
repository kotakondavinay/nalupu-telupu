package com.naltel.app.tc;

import java.util.Arrays;

public class CostOfDancing {
    public int minimum(int k, int[] costs) {
        Arrays.sort(costs);
        int ans = 0;
        for( int i = 0; i < k ; i++){
            ans += costs[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        CostOfDancing cd = new CostOfDancing();

        int n1 = 2;
        int[] a1 = {1, 5, 3, 4};
        System.out.println(cd.minimum(n1, a1));

        int n2 = 3;
        int[] a2 = {1, 5, 4};
        System.out.println(cd.minimum(n2, a2));

        int n3 = 1;
        int[] a3 = {2, 2, 4, 5, 3};
        System.out.println(cd.minimum(n3, a3));

        int n4 = 39;
        int[] a4 = {973, 793, 722, 573, 521, 568, 845, 674, 595, 310, 284, 794, 913, 93, 129, 758, 108, 433, 181, 163, 96, 932,
                703, 989, 884, 420, 615, 991, 364, 657, 421, 336, 801, 142, 908, 321, 709, 752, 346, 656, 413, 629, 801};
        System.out.println(cd.minimum(n4, a4));
    }
}
