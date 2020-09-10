import java.util.*;
//https://programmers.co.kr/learn/courses/30/lessons/43162

class Solution {
 
    static boolean[] visited;

    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited= new boolean[n+1];
        for(int i=0; i<computers.length; i++){
            if(!visited[i]){
                bfs(i, computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    public static void bfs(int v, int[][] computers){
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;
        
        while(!q.isEmpty()){
            int current = q.poll();
            for(int i=0; i<computers.length; i++){
                if(!visited[i] && computers[current][i] ==1 && current != i){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }
}