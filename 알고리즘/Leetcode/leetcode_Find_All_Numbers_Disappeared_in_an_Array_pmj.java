package Leetcode;
//https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
import java.util.*;

/**
 * 1. 문제 풀이 시간: 30분
 * 2. 풀이 방법
 *   1) 새로운 배열에 해당 숫자의 위치와 순서 표시
 *      now가 4일때 arr[4]에 1 표시 -> 0 0 0 0 1 0 0 0 0
 *   2) 다 채워진 arr를 1부터 arr.length까지 순환하면서 0인 곳을 확인
 *      0이 있다면 해당 부분 인덱스를 answer에 추가
 *   
 */

public class findAllNumbers {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        int n = nums.length;
        int[] arr = new int[n+1];

        for(int i=0; i<n; i++){
            int now = nums[i];
            if(arr[now] == 0) arr[now] = i+1;
        }

        for(int i=1; i<arr.length; i++){ //크기가 n+1개이니깐 1부터 시작해서 아직 0인 부분들 확인, 답에 추
            if(arr[i] == 0) answer.add(i);
        }


        return answer;
    }

    public static void main(String[] args) {
        int[] nums = { 4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers(nums));

    }
}
