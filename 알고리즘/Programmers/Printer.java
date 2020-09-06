import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/42587


class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        PriorityQueue p = new PriorityQueue<>(Collections.reverseOrder());
        //우선순위 큐 + 내림차순으로 정렬
        
        
        for(int i=0; i<priorities.length; i++){
            p.add(priorities[i]);
            //System.out.println(p);
        }
        //System.out.println(p);
        
        while(!p.isEmpty()){
            for(int i=0; i<priorities.length; i++){
                if(priorities[i] == (int)p.peek()){ //우선순위 큐에서 가장 우선순위가 높은 작업이 해당 배열 priorities[i]의 우선순위와 같은지 비교
                    if(i == location){ //작업 배열 priorities[]의 인덱스를 location변수와 같을 때 카운트된 것 리턴
                        return answer;
                    }
                    p.poll(); //같으면 우선순위 큐를 poll
                    answer++; //poll이 된 개수를 카운트
                }
            }
        }
        
        return answer;
    }
}