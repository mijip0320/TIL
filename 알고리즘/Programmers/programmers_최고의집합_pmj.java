package Programmers;

//https://programmers.co.kr/learn/courses/30/lessons/12938

import java.util.Arrays;

/**
 * 1. 문제 풀이 시간: 40분
 * 2. 풀이 방법
 *   1)주어진 s의 합을 n개로 나타낼때, 일단 s가 n으로 나누어 떨어지는 수로 나누고, 나머지 만큼 값을 더한다.
 *   조건1: n>s인 경우, -1 리턴
 *   조건2: 그 외의 경우 answer 배열에 s/n에 해당하는 값들을 저장
 *         -> 그 다음 s%n만큼(1) answer 배열의 값들에 더함
 */

public class bestGroup {

    public static int[] solution(int n, int s) {
        int[] answer = new int[n];

        if(n>s){
            return new int[]{-1};
        }

        for(int i=0; i<answer.length; i++){
            answer[i] = s/n;
        }

        for(int j=0; j<s%n; j++){
            answer[j]++; //첫번째 요소 증가
        }

        //오름차순으로 정렬
        Arrays.sort(answer);


        return answer;
    }

    public static void main(String[] args) {

        int[] s = solution(3,13);

        for(int x=0; x<s.length; x++){
            System.out.println(s[x]);
        }
    }
}
