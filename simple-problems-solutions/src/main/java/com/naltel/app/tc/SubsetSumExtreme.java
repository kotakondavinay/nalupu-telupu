package com.naltel.app.tc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubsetSumExtreme {
    public double getExpectation(int[] block, int[] face) {

        int B = block.length;
        int F = face.length;

        int[] sums = new int[1 << B]; //sum for each subset of blocks
        Map<Integer,Set<Integer>> masks = new HashMap<>(); //possible block subsets for each face

        for (int f : face)
            masks.put(f, new HashSet<>());

        for (int i = 1; i < sums.length; i++) {
			/*
			 * dynamic programming:
			 * 1. remove a single block from the current subset of blocks
			 * 2. the corresponding block sum was already calculated
			 * 3. add the number on the removed block to it
			 *
			 * here: always choose the block corresponding
			 * to the least significant bit of i
			 */
			int s = i & -i;
            int t = Integer.numberOfTrailingZeros(s);
            int w = i & ~(1 << t);
            sums[i] = sums[w] + block[t];
            //only add block subsets that add up to a face
            if (masks.containsKey(sums[i]))
                masks.get(sums[i]).add(i);
        }

        //expected value for each subset of (remaining) blocks
        double[] E = new double[1 << B];

        for (int i = 0; i < E.length; i++) {

            int def = sums[i];
            double[] choices = new double[F];

			/*
			 * default value for every face of the dice will be the current block sum
			 * (given the face f, if there is no subset of the blocks that sum up to f
			 *  no further blocks will be removed and the game ends)
			 */
            Arrays.fill(choices, def);

            for (int s = i; s > 0; s = (s - 1) & i) { //for each subset of i
                for (int j = 0; j < F; j++) { //for each face
                    if (masks.get(face[j]).contains(s))
						/*
						 * dynamic programming:
						 * 1. remove from i the current subset of remaining blocks that add up to a face
						 * 2. the expected value of the remaining subset of blocks was already calculated
						 * 3. store the value for later calculation of expected value of i
						 *
						 * here: want to minimize total expected value, so just find the minimum
						 * (this also means that we always have a constant number of choices (the number of faces))
						 */
                        choices[j] = Math.min(choices[j], E[i & ~s]);
                }
            }

            //calculate expected value for subset of (remaining) blocks i
            for (double e : choices)
                E[i] += e;
            E[i] /= F;
        }

        //return expected value of the set of blocks (no blocks removed)
        return E[(1 << B) - 1];
    }

    public static void main(String[] args) {
        //Got it from one of tutorials.
        SubsetSumExtreme ss = new SubsetSumExtreme();
        int[] block = {1,2,3};
        int[] face = {6,5};
        System.out.println(ss.getExpectation(block, face));

        int[] block1 = {12,11,10,9,8,7,6,5,4,3,2,1};
        int[] face1 = {12,12,12,12,12,6,6,6,3,3,2,1};
        System.out.println(ss.getExpectation(block1, face1));

    }
}