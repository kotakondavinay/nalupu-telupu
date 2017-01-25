package com.naltel.app.bowlingalley;

/**
 * Created by vinaykk on 25/01/17.
 */
public enum Bonus {
    NONE(0),
    STRIKE(10),
    SPARE(5);

    private int points;

    private Bonus(int points) {
        this.points = points;
    }
    public int getPoints() {
        return points;
    }
}
