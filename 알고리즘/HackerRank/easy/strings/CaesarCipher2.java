package com.example.Algorithm.easy.strings;
//마찬가지...
//문제: 아스키코드를 쓰면 자꾸 아스키코드에 해당되는 특수문자들이 그대로 출력된다
//해결방법: 문자열에 a-z를 넣고 거기안에서만 돌려야함

public class CaesarCipher2 {
    static String caesarCipher(String s, int k) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String answer = "";


        for(char letter: s.toCharArray()){
            if(Character.isLowerCase(letter)){ //소문자 일 때
                int encrypted = alphabet.indexOf(letter)+k; //k만큼 문자에 더함
                if(encrypted > alphabet.length()-1){ //마지막 Z를 넘어가면
                    encrypted = encrypted % alphabet.length(); //나머지에 64를 더해서 a부터 다시 시작
                }
                answer += Character.toString(alphabet.charAt(encrypted));
            }else if (Character.isUpperCase(letter)) {
                int encrypted = alphabet.toUpperCase().indexOf(letter) + k;
                if (encrypted > alphabet.length() - 1) {
                    encrypted = encrypted % alphabet.length();
                }
                answer += Character.toString(alphabet.charAt(encrypted)).toUpperCase();
            } else{
                answer += Character.toString(letter);
            }
        }


        return answer;

    }


    public static void main(String[] args) {

        int n = 11;


        String s = "Hello_World!";

        int k = 4;


        String result = caesarCipher(s, k);

        System.out.println(result);


    }


}
