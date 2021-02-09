package com.example.Algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Network2 {

    static boolean[] visited;
    public static void bfs(int v, int[][] computers){

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;

        while(!queue.isEmpty()){
            int x = queue.poll();
            //System.out.println(x + " ");
            for(int j=0; j<computers.length; j++){
                if(computers[x][j] == 1 && x != j && !visited[j]){
                    visited[j] = true;
                    queue.offer(j);
                }
            }
        }

    }



    public static void main(String[] args) {

        int answer = 0;
        int n = 3;
        int[][] computers= {{1,1,0},{1,1,0},{0,0,1}};
        visited = new boolean[n+1];

        for(int i=0; i<computers.length; i++){
            if(!visited[i]){
                bfs(i, computers);
                answer ++;
            }
        }



        System.out.println(answer);
    }

}
