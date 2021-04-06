package Programmers;
//https://programmers.co.kr/learn/courses/30/lessons/42626


import java.util.PriorityQueue;

/**
 * 1. 문제 풀이 시간: 40분
 * 2. 풀이 방법
 *   1)우선순위 큐 사용(순서대로 정렬)
 *   2)큐 사이즈가 1일때 -1 리턴(예외 처리)
 *      2-1)계산된 pq.peek()가 K보다 커질때까지 새로운 스코빌 지수 계산
 *      2-2)새로운 스코빌 지수가 계산될때 answer++
 */

public class moreSpicy {
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i< scoville.length; i++){
            pq.add(scoville[i]);
        }

        while(pq.peek() <= K){ //pq 항목이(sum) K보다 작을 때 반복
            if(pq.size() == 1){ //힙 사이즈가 1일때(예외처리)
                return -1;
            }
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + (second*2);

            pq.add(sum);
            answer++;
        }



        return answer;
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K=7;
        System.out.println(solution(scoville,K));
    }
}
