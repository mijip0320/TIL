package programmers;
//https://programmers.co.kr/learn/courses/30/lessons/43165

/**
 * 1. 문제풀이시간: 40분
 * 2. 풀이 방법:
 * 1) DFS 활용
 *  1-1)index에 해당하는 숫자를 +와 -로 배열에 넣어줌
 *  1-2)모두 탐색 후 numbers 배열을 다 더해서 target과 일치하면 answer++, 아니면 다시 탐색
 *  
 */

public class targetNumber {
    static int answer = 0;
    public static int solution(int[] numbers, int target){

        dfs(numbers, target, 0);
        return answer;
    }
    public static void dfs(int[] numbers, int target, int index){
        if(index == numbers.length){
            int sum = 0;
            for(int i=0; i<numbers.length; i++){
                sum+=numbers[i];
            }
            if(sum==target){
                answer++;
            }
        }else{
            numbers[index]*=1;
            dfs(numbers,target,index+1);

            numbers[index]*=-1;
            dfs(numbers,target,index+1);
        }
    }

    public static void main(String[] args) {

        int[] numbers = {1,1,1,1,1};
        int target = 3;


        System.out.println(solution(numbers,target));
    }
}
