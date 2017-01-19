/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naltel.app.misc;

import java.util.*;

/**
 *
 * @author vinaykk
 */
public class SlotOutputTime {

    static class Interval /*implements Comparable<Interval> */ {

        double start;
        double end;

        Interval(double s, double e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "Start " + start + " End " + end;
        }

        /*
        @Override
        public int compareTo(Interval o1) {
            return (int) (this.start - o1.start);
        }
*/
    }

    public static Map<Double, Integer> getMapOutput() {
        Map<Double, Integer> map = new HashMap();
        List<Interval> intervalList = new ArrayList();
        Interval intr = new Interval(1.2, 4.5);
        intervalList.add(intr);
        intr = new Interval(1.3, 3.0);
        intervalList.add(intr);
        intr = new Interval(3.1, 6.7);
        intervalList.add(intr);
        intr = new Interval(8.9, 10.3);
        intervalList.add(intr);

        //Collections.sort(intervalList);
        Collections.sort(intervalList, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                int returnVal =  (int) (o1.start - o2.start);
                /* if(returnVal == 0) {
                    returnVal = (int) (o1.end - o2.end);
                }*/
                return returnVal;
            }
        });
        /* Collections.sort(intervalList, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return  (int) (o1.end - o2.end);
            }
        });*/
        for (Interval i : intervalList) {
            map.put(i.start, 0);
            map.put(i.end, 0);
        }
        Interval prev = intervalList.get(0);
        map.put(prev.start, 1);
        for (int i = 1; i < intervalList.size(); i++) {
            Interval curr = intervalList.get(i);
            map.put(curr.start, 1);
            if (curr.start > prev.start && curr.start < prev.end) {
                map.put(curr.start, map.get(prev.start) + 1);
                if (curr.end > prev.start && curr.end < prev.end) {
                    map.put(curr.end, map.get(prev.end) + 1);
                } else {
                    map.put(curr.end, map.get(prev.end));
                    map.put(prev.end, map.get(prev.end) + 1);
                }
            }
            prev = curr;
        }
        System.out.println(map);
       
       return map;
    }
}
