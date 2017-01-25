package com.naltel.app.bowlingalley;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vinaykk on 25/01/17.
 */
public class BowlingAlleyGame {
    List<BowlingLane> bowlingLanes;

    public BowlingAlleyGame(List<BowlingLane> bowlingLanes) {
        this.bowlingLanes = bowlingLanes;
    }


    public static void main(String[] args) throws InterruptedException {
        Player p1 = new Player("vinay");
        Player p2 = new Player("subbu");
        Player p3 = new Player("murthy");
        List<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        BowlingLane bl1 = new BowlingLane(players, 1);
        List<BowlingLane> bowlingLanes = new ArrayList<BowlingLane>();
        bowlingLanes.add(bl1);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(bl1);
        Thread.sleep(10000l);
        System.out.println(p1.getTotalScore());
        System.out.println(p2.getTotalScore());
        System.out.println(p3.getTotalScore());
        System.out.println(bl1.getWinner());

    }
}
