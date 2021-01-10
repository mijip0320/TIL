package com.example.Algorithm.easy.implementation;
import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;

public class MigratoryBirds {
    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
        int answer = 0;
        int count = 0; //타입에 해당하는 새 마리 세기

        int[] common = new int[5]; //count할 배열 생성

        for(int i=0; i<arr.size(); i++){ //count할 배열에 입력값에 대한 타입 count 증가
           common[arr.get(i) - 1]++;
        }
//[1 2 3 4 5 4 3 2 1 3 4]
        for(int j=0; j<common.length; j++){
            if(count < common[j]){
                count = common[j]; //count에 최대값 대입(더 큰 최댓값이 존재한다면 바꿈)
                answer = j+1; //해당 인덱스+1을 정답에 대입
            }
        }


        return answer;
    }

    public static void main(String[] args) throws IOException {


        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(4);
        arr.add(3);
        arr.add(2);
        arr.add(1);
        arr.add(3);
        arr.add(4);


        int result = migratoryBirds(arr);

        System.out.println(result);


    }

}
