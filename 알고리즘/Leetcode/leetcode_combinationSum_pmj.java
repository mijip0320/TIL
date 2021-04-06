package Leetcode;

import java.util.*;
/**
 * 1. 문제 풀이 시간: 초과
 * 2. 풀이 방법
 *   1) 백트래킹 사용
 *   2) 현재 candidate 값이 target 보다 작으면 temp 리스트에 넣은 뒤 다음 target 을 target - candidate 하여 계속 호출
 *   3) 만약 target 값이 0이 된다면 temp 에 있는 값들의 합이 target 이라는 뜻이므로 result 리스트에 넣어주면서 돌아감
 */

public class combinationSum {
    static List<List<Integer>> result = new ArrayList<List<Integer>>();
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates,target));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        for (int i = 0; i < len; i++) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.add(candidates[i]);
            backtracking(candidates, i, 1, target - candidates[i], temp);
        }

        return result;
    }

    public static void backtracking(int[] candidates, int index, int tempSize, int target, List<Integer> temp){
        if (target == 0) {
            result.add(new ArrayList(temp));
            return;
        }

        int len = candidates.length;
        //이전값을 볼 필요가 없기 때문에 index부터 진행
        for (int i = index; i < len; i++) {
            if (candidates[i] <= target) {
                temp.add(candidates[i]);
                backtracking(candidates, i, tempSize + 1, target - candidates[i], temp);
                temp.remove(tempSize);
            }
        }
    }
}
