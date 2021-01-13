package com.example.Algorithm.programmers;
import java.util.*;

//해결방법: 굳이 변수두개로 해서 모두 돌릴 필요없이 전체 배열의 최소공배수 이기 때문에 하나를 기준으로 잡고 나머지 데이터와 최소공배수를 차례대로 구하는 방식을 사용

public class lcm2 {


    public static int solution(int[] arr) {
        int answer = 0;
        int lcm1 = arr[0]; //하나를 기준으로 잡음

        for(int i=0; i<arr.length; i++){
//            for(int j=i+1; j<arr.length-1;j++){
//                answer = lcm(arr[i], arr[j]);
                lcm1 = lcm(lcm1, arr[i]);
//            }

        }
//        return answer;
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

    public static void main(String[] args){

        int[] arr = {2,6,8,14};


        int result = solution(arr);

        System.out.println(result);
    }
}
