package com.naltel.app.snakesandladders;

/**
 * Created by vinaykk on 05/02/17.
 */
public class DiceUtil {

    public static final Integer diceMaxValue = 6;
    public static int dice(String playerName) {
        int MaxPoints = diceMaxValue;
        double val = Math.random();
        int score = (int) (val * MaxPoints + 1);
        System.out.println(" PlayerName:"+ playerName + "Dice score:"+ score);
        return score;
    }
}
