import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/42747?language=java

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        //배열 오름차순 정렬
        Arrays.sort(citations);
       
        //h번 이상 인용된 논문이 h편 이상인 h의 최댓값이 답
        for(int i=0; i<citations.length; i++){
            int h = citations.length - i;
            
            
            if(citations[i] >= h){
                answer = h;
                break;
            }
            
        }
        
        return answer;
    }
}
