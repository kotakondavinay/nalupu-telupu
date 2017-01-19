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
public class JavaApplicationTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // 1. isPalindrome.
        //boolean y = Palindrome.isPalindrome(1221);
        // 2. ExpressEvaluation.
        //int x = ExpressionEvaluation.expressionEval("5*4/2");
        //System.out.println(x);

        //3. getPalindromeicPaths.
        /*
        int R=3,C=4;
        char mat[][] =
                new char[][] {
                {'a', 'a', 'a', 'b'},
                {'b', 'a', 'a', 'a'},
                {'a', 'b', 'b', 'a'}
                };
        System.out.println(MatrixPalindromePaths.getPalindromePaths(mat, R, C));
        */
        //4. LongestSubSequenceDistanceOne.
        /*
        int[] array = new int[]{1, 2, 3, 4, 5, 3, 2};;
        int length = LongestSubSequenceDistanceOne.getLongestSubSequenceDistance(array, array.length);
        System.out.println(length);
        */
        //SlotOutputTime.getMapOutput();
        /* int returnVal;
        LFUCache cache = new LFUCache(2);
        //returnVal = cache.get(0);
        //System.out.println(returnVal);
        //cache.set(0, 0);
        //returnVal = cache.get(0);
        //System.out.println(returnVal);
        cache.set(2, 1);
        cache.set(1, 1);
        cache.set(2, 3);
        cache.set(4, 1);
        returnVal = cache.get(1);       // returns 1
        System.out.println(returnVal);
        //cache.set(3, 3);    // evicts key 2
        returnVal = cache.get(2);       // returns -1 (not found)
        System.out.println(returnVal);*/

        /* returnVal = cache.get(3);       // returns 3.
        System.out.println(returnVal);
        cache.set(4, 4);    // evicts key 1.
        returnVal = cache.get(1);       // returns -1 (not found)
        System.out.println(returnVal);
        returnVal =  cache.get(3);       // returns 3
        System.out.println(returnVal);
        returnVal = cache.get(4);
        System.out.println(returnVal);
        */
        //CoinsChange.getCoinsChange();
       // BalancedParanthesis.getFunc();
        System.out.println(Nthroot.root(81, 4));
        //LCS.getLCSLength();
    }    
}
