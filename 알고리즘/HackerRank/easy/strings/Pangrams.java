package com.example.Algorithm.easy.strings;

import java.util.Locale;

public class Pangrams {

    // Complete the pangrams function below.
    static String pangrams(String s) {
        String lower = s.toLowerCase(); //모두 소문자로 바꿈

//        int[] check = new int[26];

        for(char letter = 'a'; letter <= 'z'; letter++){
            if(lower.indexOf(letter) < 0){ ///무슨 의미를 하는건지???????
                return "not pangram";
            }
        }
        return "pangram";

    }



    public static void main(String[] args){

        String s = "We promptly judged antique ivory buckles for the prize";

        String result = pangrams(s);

        System.out.println(result);

    }
}
