package com.example.Algorithm.easy.strings;

public class HakcerRankinaString2 {

    public static String hackerrankInString(String s) {
       String hackerrank = "hackerrank";
       int sIdx = 0;
       int hackerIdx = 0;

       while(sIdx < s.length()){
           if(hackerIdx == hackerrank.length()-1){
               return "YES";
           }

           if(s.charAt(sIdx) == hackerrank.charAt(hackerIdx)){
               hackerIdx++;
           }

           sIdx++;
       }
    return "NO";
    }

    public static void main(String[] args) {
        String s = "hereiamstackerrank";



        String result = hackerrankInString(s);

        System.out.println(result);

    }
}
