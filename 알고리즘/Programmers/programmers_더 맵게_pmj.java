package Programmers;
//https://programmers.co.kr/learn/courses/30/lessons/42626


import java.util.PriorityQueue;

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
