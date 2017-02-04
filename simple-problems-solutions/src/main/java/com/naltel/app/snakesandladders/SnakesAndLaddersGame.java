package com.naltel.app.snakesandladders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinaykk on 05/02/17.
 */
public class SnakesAndLaddersGame {
    private List<Player> players;
    private SLBoard board;
    boolean isGameEnded;
    private Player winner;

    public SnakesAndLaddersGame(List<Player> players, SLBoard board) {
        this.players = players;
        this.board = board;
        this.isGameEnded = false;
        for (Player player:players) {
            if(player.isGameWon()) {
                this.isGameEnded = true;
            }
        }
    }

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        Player p1 = new Player("vinay");
        Player p2 = new Player("subbu");
        Player p3 = new Player("murthy");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        SLBoard board = new SLBoard();
        SnakesAndLaddersGame game = new SnakesAndLaddersGame(players, board);
        game.play();
    }

    public void play() {
        if(isGameEnded) {
            System.out.println("you can not play game: the game is already over:");
        }

        while (!isGameEnded) {
            for (Player player: players) {
                player.move(board);
                if(player.isGameWon()) {
                    isGameEnded = true;
                    winner = player;
                    break;
                }
            }
        }
        System.out.println("Winner is :"+ winner.getName());
    }
}
