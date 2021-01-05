package com.algorithm.easy.implementation;

public class BreakingTheRecords {

    // 풀이 방식
//    1. 최댓값, 최솟값, 각각 갱신될 때 마다 count되는 정수 선언
//    2. scores를 순회하면서
//            2-1. 두 수(최솟값, scores)를 비교할때, 두번째 수가 첫번째 수보다 작다면 최솟값 갱신, 최솟값 갱신에 대한 count 증가
//            2-2. 최댓값과 scores를 비교할 때 두번째 수가 첫번째 수보다 크다면 최댓값 갱신, 최댓값 갱신에 대한 count 증가

    static int[] breakingRecords(int[] scores) {
        int max = scores[0];
        int min = scores[0];
        int countHigh = 0;
        int countLow = 0;

        int[] answer = new int[2];

        for(int i=0; i< scores.length; i++){


            //max 갱신
            if(max < scores[i]){
                max = scores[i];
                countHigh++;
            }else if(min > scores[i] ){ //min 갱신
                min = scores[i];
                countLow++;
            }
        }

        answer[0] = countHigh;
        answer[1] = countLow;


        return answer;

    }

    public static void main(String[] args) {
        int[] scores = {10, 5, 20, 20, 4, 5, 2, 25, 1};
        int[] result = breakingRecords(scores);


        for(int i=0; i<result.length; i++){
            System.out.print(result[i]+ " ");
        }


    }

}
