package com.example.Algorithm.programmers;
import java.util.*;


//문제: https://programmers.co.kr/learn/courses/30/lessons/12953?language=java
//오류: 마지막 두 수의 최소공배수만 구해지게 되었다.
//해결방법: lcm2

public class lcm {


    public static int solution(int[] arr) {
        int answer = 0;
        int gcm = 0;

        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length-1;j++){
               answer = lcm(arr[i], arr[j]);
            }

        }
        return answer;

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
