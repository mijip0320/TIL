import java.util.*;

/**
 * 1. 문제풀이시간: 40분
 * 2. 컴퓨팅 사고
 *    유클리드 호제법을 이용해 진행
 * (1) 반복문으로 최대공약수 찾기
 * (2) 최대 공약수를 이용해 최소 공배수 찾기
 *
 */

class Solution {
    public int solution(int[] arr) {

        int lcm1 = arr[0]; 

        for(int i=0; i<arr.length; i++){
                lcm1 = lcm(lcm1, arr[i]);
        }

          return lcm1;
    }

    public static int gcd(int a, int b){
        while( b!= 0){
            int tmp;
            tmp = a%b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static int lcm(int a, int b){
        return (a*b) / gcd(a, b);
    }
}
