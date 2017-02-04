package com.naltel.app.snakesandladders;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinaykk on 05/02/17.
 */
public class SLBoard {

    public static final Integer maxPoints = 100;

    private Map<Integer, Integer> snakesMap;
    private Map<Integer, Integer> laddersMap;

    public SLBoard() {
        this.snakesMap = new HashMap<Integer, Integer>();
        snakesMap.put(23, 10);
        snakesMap.put(45, 25);
        snakesMap.put(61, 33);
        snakesMap.put(76, 51);
        snakesMap.put(98, 5);
        this.laddersMap = new HashMap<Integer, Integer>();
        laddersMap.put(4, 25);
        laddersMap.put(21, 45);
        laddersMap.put(35, 71);
        laddersMap.put(47, 82);
        laddersMap.put(9, 73);
    }

    public int getNextPosition(int position, String playerName) {
        if(snakesMap.containsKey(position)) {
            System.out.println("Player:" + playerName+ "was eaten by Snake and moved from "+ position+ " to " +snakesMap.get(position) );
            return snakesMap.get(position);
        }
        if(laddersMap.containsKey(position)) {
            System.out.println("Player:" + playerName+ "has got ladder and moved from "+ position+ " to " +laddersMap.get(position) );
            return laddersMap.get(position);
        }
        System.out.println("Player:" + playerName+ "has moved to "+ position );
        return position;
    }
}
