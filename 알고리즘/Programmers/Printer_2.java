import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/42587

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        
        Queue<Printer> q = new LinkedList<>();
        
        for(int i=0; i<priorities.length; i++){ //큐에 추가
            q.add(new Printer(i, priorities[i])); //location(인덱스 번호)과 해당 값(가중치) 추가

        }
        
        while(!q.isEmpty()){
           
            boolean flag = false;
			int com = q.peek().prior;
			for (Printer p : q) { //큐의 값들을 하나씩 Printer p로 꺼내보기
				if (com < p.prior) { // 맨앞의 수보다 큰 숫자가 존재하면
					flag = true;

				}
			}
            
            if(flag){ //맨 앞의 숫자가 가장 큰 것이 아니라면 그 숫자를 맨 뒤로 보낸다
                q.offer(q.poll());
			} else {// 현재 맨앞의 숫자가 가장 클 때(flag = false일때)
				if (q.poll().location == location) { //초기에 설정한 Printer 객체의 location과 주어진 location이 같다면
                    //배열 길이에서 q의 poll 된 이후의 크기를 뺌
					answer = priorities.length - q.size();
				}
			}
        }
        
        return answer;
    }
    class Printer {
		int location;
		int prior;

		Printer(int location, int prior) {
			this.location = location;
			this.prior = prior;
		}
	}
    
}
