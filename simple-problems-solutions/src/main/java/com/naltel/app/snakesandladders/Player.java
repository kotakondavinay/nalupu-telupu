package com.naltel.app.snakesandladders;

/**
 * Created by vinaykk on 05/02/17.
 */
public class Player {
    private String name;
    private int position;
    private boolean isGameWon;

    public Player(String name) {
        this.name = name;
        this.isGameWon = false;
    }

    public void move(SLBoard board) {
        int diceValue = DiceUtil.dice(name);
        if(SLBoard.maxPoints < position+diceValue) {
            int nextPosition = board.getNextPosition(position, name);
            this.position = nextPosition;
            isGameWon = false;
            return;
        }
        if(SLBoard.maxPoints == position+diceValue) {
            int nextPosition = board.getNextPosition(position+diceValue, name);
            this.position = nextPosition;
            isGameWon = true;
            return;
        }
        int nextPosition = board.getNextPosition(position+diceValue, name);
        this.position = nextPosition;
        if(diceValue == DiceUtil.diceMaxValue) {
             move(board);
        }
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public String getName() {
        return name;
    }
}
