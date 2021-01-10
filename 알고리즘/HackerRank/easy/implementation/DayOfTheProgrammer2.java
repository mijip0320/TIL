package com.algorithm.easy.implementation;

public class DayOfTheProgrammer2 {

    /*문제점:
    * 1. 날짜(일)를 계산할 필요 x: 어짜피 다 256에서 빼기 때문에 나올 날짜는 12.09 아님 13.09
    * 2. 그레고리안과 줄리안이 바뀌는 시점에 따라 날짜가 조금씩 달라짐(조건 다시 확인 필요)
    * 3. Special case: transition year 고려(1918)
    * */

    static String dayOfProgrammer(int year) {


        String answer = "";

        if(year < 1918){
            answer += (year % 4 ==0) ? "12.09"+year : "13.09" + year;
        }else if(year == 1918){
            answer += "26.09"+year;
        }else{
            //in the Gregorian calendar, leap years are either of the following:
            //1. Divisible by 400.
            //2. Divisible by 4 and not divisible by 100.
            answer += ((year%400 ==0) || (year % 4==0 && year %100 != 0)) ? "12.09"+year : "13.09" + year;
        }

        return answer;
    }

    public static void main(String[] args) {
//        int year = 2017;
        int year = 2100;
        String result = dayOfProgrammer(year);

        System.out.println(result);

    }

}
