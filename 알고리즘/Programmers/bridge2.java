package com.example.Algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

//https://jaebworld.tistory.com/42
//해결 안됨

public class bridge2 {

    public static void main(String[] args){
        int count = 0; //다리를 건넌 횟수
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7,4,5,6};
        int sumWeight = 0; //다리 위에 있는 트럭들의 총 무게
        Queue<Integer> bridge = new LinkedList<>(); //현재 다리 위에 있는 트럭들

        //처음 지나는 트럭에 해당
        for(int i=0; i<bridge_length-1;i++) bridge.offer(0);
        bridge.offer(truck_weights[0]);
        sumWeight += truck_weights[0];
        count++;

        int idx = 1;

        while(true){
            if(idx == truck_weights.length) break; //트럭들이 모두 건넜을 경우

            if(bridge.size() == bridge_length){//다리 위에 있는
                sumWeight -= bridge.poll();
            }


        }
        System.out.println(count);

    }

}
