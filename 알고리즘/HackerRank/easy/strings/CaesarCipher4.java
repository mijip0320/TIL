package com.example.Algorithm.easy.strings;

import java.util.Locale;

public class CaesarCipher4 {
    static String caesarCipher(String s, int k) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String answer = "";

        for(char ch : s.toCharArray()){
            if(Character.isLowerCase(ch)){
                int change = alphabet.indexOf(ch)+k;
                if(change > alphabet.length()-1) change = change%alphabet.length();
                answer += Character.toString(alphabet.charAt(change));
            }else if(Character.isUpperCase(ch)){
                int change = alphabet.toUpperCase().indexOf(ch)+k;
                if(change > alphabet.length()-1) change = change%alphabet.length();
                answer += Character.toString(alphabet.charAt(change)).toUpperCase();
            }else{
                answer += Character.toString(ch);
            }

        }

        return answer;

    }


    public static void main(String[] args) {


        String s = "middle-Outz";

        int k = 2;


        String result = caesarCipher(s, k);

        System.out.println(result);
    }

}
