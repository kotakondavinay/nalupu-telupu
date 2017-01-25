package com.naltel.app.bowlingalley;

/**
 * Created by vinaykk on 25/01/17.
 */
public class BowlingUtils {

    public static int Bowl(int laneNumber, String playerName, int MaxPoints) {
    	double val = Math.random();
        int score = (int) (val * MaxPoints + 1);
        System.out.println("lane Number:"+ laneNumber + " PlayerName:"+ playerName + " score:"+ score);
        return score;
    }

}
