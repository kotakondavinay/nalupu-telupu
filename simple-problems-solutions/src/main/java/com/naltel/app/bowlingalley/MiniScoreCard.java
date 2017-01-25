package com.naltel.app.bowlingalley;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinaykk on 25/01/17.
 */
public class MiniScoreCard {
    private int firstPoints;
    private int secondPoints;
    private int thirdPoints;

    List<Bonus> bonusList;

    public MiniScoreCard() {
        this.bonusList = new ArrayList<Bonus>();
        firstPoints = 0;
        secondPoints = 0;
        thirdPoints = 0;
    }

    public void addBonus(Bonus bonus) {
        this.bonusList.add(bonus);
    }

    public void setFirstPoints(int points) {
        this.firstPoints = points;
    }

    public void setSecondPoints(int points) {
        this.secondPoints = points;
    }

    public void setThirdPoints(int points) {
        this.thirdPoints = points;
    }

    public int getScore() {
        int score = firstPoints + secondPoints + thirdPoints;
        for (Bonus bonus: bonusList) {
            score += bonus.getPoints();
        }
        return score;
    }
}
