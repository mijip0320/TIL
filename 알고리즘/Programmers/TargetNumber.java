import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/43165

class Solution {
   static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers, target, 0);
        return answer;
    }
    
   public static void dfs(int[]numbers, int target, int node){
       if(node == numbers.length){
           int sum = 0;
           for(int num : numbers) sum += num;
           if(sum == target){
               answer++;
           }
       }else{
           numbers[node] *= 1;
           dfs(numbers, target, node+1);
           
           numbers[node] *= -1;
           dfs(numbers,target,node+1);
       }
   }
  
}