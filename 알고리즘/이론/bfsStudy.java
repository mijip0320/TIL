package com.example.Algorithm.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfsStudy {
    static int[][] map;
    static boolean[] visited;
    static StringTokenizer st;
    static int N,M,start,end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int m=0; m<M; m++){ //그래프 정보 입력받기
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            map[start][end] = 1;
            map[end][start] = 1;
        }

        System.out.print("그래프 BFS 방문 순서: ");
        bfs(1);
    }

    static void bfs(int point){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(point);
        visited[point] = true;

        while(!queue.isEmpty()){
            int x = queue.poll();
            System.out.print(x + " ");
            for(int i=1; i<=N; i++){
                if(map[x][i] == 1 && visited[i] == false){ //다음 정점과 연결되어 있고 아직 방문하지 않았을 때
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}
