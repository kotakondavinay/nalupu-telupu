package com.naltel.app.tc;

public class DerangementsDiv2 {
    public static int count(int n, int m){
        long fact[] = new long[101];
        int pasc[][] = new int[101][101];
        int mod = 1000000007;
        fact[0]=1;
        for(int i = 1; i < 101; i++) {
            fact[i]=(fact[i-1] * i)%mod;
        }
        for(int i = 0; i < 101; i++) {
            pasc[i][0] = 1;
            for(int j = 1; j < i; j++) {
                pasc[i][j] = ( pasc[i-1][j-1] + pasc[i-1][j]) %mod;
            }
            pasc[i][i] = 1;
        }
        long ans = fact[n+m];
        for(int i = 1; i <= m; i++ ) {
            int coefficient = pasc[m][i];
            if(i%2 == 0)
                ans = (ans + ((coefficient * fact[m+n-i])%mod))%mod;
            else
                ans = (ans + ((-coefficient * fact[m+n-i])%mod))%mod;
        }
        return (int)ans;
    }
    public static void main(String[] args) {
        System.out.println(count(1, 2));
    }
}
