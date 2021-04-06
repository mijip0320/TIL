package Programmers;
//https://programmers.co.kr/learn/courses/30/lessons/12900

/**
 * 1. 문제 풀이 시간: 초과
 * 2. 풀이 방법
 *   1)DP 사용
 *   2)가로의 길이가 n일 경우 n-1까지 타일이 꽉 차 있는 경 채울 수 있는 가로의 길이가 1밖에 안됨(1개 타일)
 *   3)n-2 타일까지 꽉 차있는 경우 가로 길이가 2가 남음
 *      3-1) 세로로 타일 2개 채울 수 있음(2번에 해당)
 *      3-2) 가로로 타일 1개 채울 수 있음
 *   4)n-3 타일까지 꽉 차 있는 경우? 2번과 3번 케이스에 포함..
 *      4-1) F(n-3) = F(n-2) + F(n-1)
 *      F(n) = F(n-2) + F(n-1)
 */
public class tiling {
    public static int solution(int n) {

        int dp[] = new int[n+1];

        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(solution(n));

    }
}
