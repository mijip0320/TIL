package com.example.Algorithm.easy.strings;

public class MarsExploration {
    // Complete the marsExploration function below.
    static int marsExploration(String s) {
        String sos = "SOS";
        int answer = 0;


        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) != sos.charAt(i%3)){
                answer++;
            }
        }

        return answer;

    }


    public static void main(String[] args) {


        String s = "SOSSOT";

        int result = marsExploration(s);

        System.out.println(result);

    }
}
