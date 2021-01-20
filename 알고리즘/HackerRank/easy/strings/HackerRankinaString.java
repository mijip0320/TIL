package com.example.Algorithm.easy.strings;

import java.util.Arrays;

//다시 풀어보기

public class HackerRankinaString {

    // Complete the hackerrankInString function below.
    static String hackerrankInString(String s) {
    String correct = "hackerrank";
    int sIdx = 0;
    int hackerIdx = 0;
    while(sIdx < s.length()){
        if(hackerIdx == correct.length()-1){
            return "YES"; //같은 알파벳의 인덱스 수가 correct의 크기 -1(인덱스 수)와 같을 때
        }
        if(s.charAt(sIdx) == correct.charAt(hackerIdx)){
            hackerIdx++; //같은 알파벳일 때 hackerIdx 증가
        }
        sIdx++; //s 인덱스 증가 (일치 하지 않을때 s 인덱스만 증가 시켜서 correct 알파벳과 다시 비교
    }
        return "NO";
    }


    public static void main(String[] args) {
       String s = "hereiamstackerrank";



       String result = hackerrankInString(s);

        System.out.println(result);

    }

}
