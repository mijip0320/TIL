package com.algorithm.easy.implementation;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;


import java.util.ArrayList;

public class BetweenTwoSets {
    /* 풀이 방법(참고):

       - 첫 번째 array들의 최소 공배수를 구함.
       - 두 번째 array들의 최대 공약수를 구함
       - 최소 공배수의 배수 ( 최소 공배수 * 1, 최소 공배수 * 2...)를 해서 최대 공약수를 나누었을 때 나머지가 0인 인수들을 체크

     */

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        int lcmInt = lcm(a); //최소 공배수
        int gcdInt = gcd(b); //최대 공약수

        int count = 0;
        int index = 1;
        int comp = 0;

        while(comp < gcdInt){
            comp = lcmInt * index;

            if(gcdInt % comp == 0){ //최소 공배수의 배수 ( 최소 공배수 * 1, 최소 공배수 * 2...)를 해서 최대 공약수를 나누었을 때 나머지가 0인 인수들을 체크
                count++;
            }
            index++;
        }
        return count;

    }

    public static int gcd(int a, int b){
        while(b > 0){
            int temp = b;
            b = a % b;
            a=temp;
        }

        return a;
    }

    public static int gcd(List<Integer> input){
        int result = input.get(0);

        for(int i=1; i<input.size(); i++){
            result = gcd(result, input.get(i));
        }

        return result;
    }

    public static int lcm(int a, int b){ //최소 공배수 구하는 함수
        return (a * b) / gcd(a, b);
    }

    public static int lcm(List<Integer> input){
        int result = input.get(0);

        for(int i = 1; i < input.size(); i++) {
            result = lcm(result, input.get(i));
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        a.add(2);
        a.add(4);

        b.add(16);
        b.add(32);
        b.add(96);

        int result = getTotalX(a, b);

        System.out.println(result);
    }

}

