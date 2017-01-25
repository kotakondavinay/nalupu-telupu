package com.naltel.app.bowlingalley;

/**
 * Created by vinaykk on 25/01/17.
 */
public class Player {
    private String name;
    private int totalScore;

    public Player(String name) {
        this.name = name;
        this.totalScore = 0;
    }

    public void setTotalScore(int score) {
        this.totalScore += score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getName() {
        return name;
    }
}
