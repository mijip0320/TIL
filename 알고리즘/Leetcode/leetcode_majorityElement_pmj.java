package leetcode;
//https://leetcode.com/problems/majority-element/


import java.util.HashMap;
import java.util.Map;

/**
 * 1. 문제풀이시간: 50분
 * 2. 풀이 방법: HashMap 사용
 * 1) 맵에 아무것도 없을때 또는 처음 숫자가 등장했을 때(주의!: 만약 nums의 크기가 1이면 해당 숫자가 answer)
 *  1-1)해당 숫자를 key로, 숫자의 count를 value로 대입
 * 2)맵에 이미 key에 해당 숫자가 존재할 때
 *   2-1) 해당 숫자(key)값에 대한 value값을 count에 대입
 *   2-2) nums의 사이즈만큼 돌면서 count가 n보다 크면 answer에 대입(조건에 해당),
 *        아니라면 해당 숫자를 key로, 이미 있는 value값에 +1
 *
 */

public class majorityElement {
    public static int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int answer = 0;
        int n = nums.length/2;
        for(int i=0; i<nums.length; i++){
            if(map.isEmpty() || !map.containsKey(nums[i])){
                if(nums.length == 1){
                    answer = nums[i];
                    break;
                }
                map.put(nums[i],1);

            }else{
                int count = map.get(nums[i])+1;
                if(count > n){
                    answer = nums[i];
                }else{
                    map.put(nums[i], map.get(nums[i])+1);
                }
            }
        }


        return answer;

    }

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }
}
