package com.naltel.app.bowlingalley;

import java.util.List;

/**
 * Created by vinaykk on 25/01/17.
 */
public class BowlingLane implements Runnable {

    int laneNumber;
    List<Player> players;
    public int turns = 10;
    public int pins = 10;
    public int currentRound = 1;

    public BowlingLane(List<Player> players, int laneNumber) {
        this.players = players;
        this.currentRound = 1;
        this.laneNumber = laneNumber;
    }

    public void run() {
        startLane();
    }

    public String getWinner() {
        int winnerValue = players.get(0).getTotalScore();
        String playername = players.get(0).getName();
        for(Player player: players) {
                if(player.getTotalScore() > winnerValue) {
                    winnerValue = player.getTotalScore();
                    playername = player.getName();
                }
        }
        return playername;
    }

    public void startLane() {
        while(currentRound <= turns) {
            for(Player player: players) {
                play(player, currentRound);
            }
            currentRound++;
        }

    }

    public void play(Player player, int currentRound) {
        MiniScoreCard miniScoreCard = new MiniScoreCard();
        int numOfPinsRemaining = pins;
        int attempt1 = BowlingUtils.Bowl(laneNumber, player.getName(), pins);
        if(attempt1 == pins) {
            miniScoreCard.addBonus(Bonus.STRIKE);
        }
        miniScoreCard.setFirstPoints(attempt1);
        numOfPinsRemaining = numOfPinsRemaining - attempt1;
        if(numOfPinsRemaining != 0) {
            int attempt2 = BowlingUtils.Bowl(laneNumber, player.getName(), numOfPinsRemaining);
            if (attempt1 + attempt2 == pins) {
                miniScoreCard.addBonus(Bonus.SPARE);
            }
            miniScoreCard.setSecondPoints(attempt2);
            numOfPinsRemaining = numOfPinsRemaining - attempt2;
        }
        if(numOfPinsRemaining == 0 && currentRound == 10) {
            if(attempt1 == pins) {
                numOfPinsRemaining = pins;
                int attempt2 = BowlingUtils.Bowl(laneNumber, player.getName(), pins);
                if(attempt2 == pins) {
                    miniScoreCard.addBonus(Bonus.STRIKE);
                }
                miniScoreCard.setSecondPoints(attempt2);
                numOfPinsRemaining = numOfPinsRemaining - attempt2;
                if(numOfPinsRemaining != 0) {
                    int attempt3 = BowlingUtils.Bowl(laneNumber, player.getName(), numOfPinsRemaining);
                    if(attempt2 + attempt3 == pins) {
                        miniScoreCard.addBonus(Bonus.SPARE);
                    }
                    miniScoreCard.setThirdPoints(attempt3);
                }
            } else {
                numOfPinsRemaining = pins;
                int attempt3 = BowlingUtils.Bowl(laneNumber, player.getName(), pins);
                if(attempt3 == pins) {
                    miniScoreCard.addBonus(Bonus.STRIKE);
                }
                miniScoreCard.setThirdPoints(attempt3);
            }
        }
        player.setTotalScore(miniScoreCard.getScore());
    }
}
