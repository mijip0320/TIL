package com.algorithm.easy.implementation;

import java.util.ArrayList;
import java.util.List;

public class SubArrayDivision {
    // Complete the birthday function below.
    static int birthday(List<Integer> s, int d, int m) {
    int count = 0;
    //int[] sub = new int[];


    for(int i=0; i<s.size(); i++){ //전체 리스트에 대해서
        int sum = 0;
       for(int j=i; j<m+i; j++){ //m만큼 split
           if( s.size() >= i+m){ //split을 할때 전체 리스트 크기를 초과하지 않을 때
               sum += s.get(j); //sum에 더함
           }
       }
       if(sum == d){ //sum이 d와 같을 때(생일 날ㅉㅏ)
           count++; //정답 증가
       }

    }


    return count;
    }

    public static void main(String[] args)  {
        List<Integer> s = new ArrayList<Integer>();

        s.add(1);
        s.add(2);
        s.add(1);
        s.add(3);
        s.add(2);

        int d = 3;

        int m = 2;

        int result = birthday(s, d, m);

        System.out.println(result);
    }

}
