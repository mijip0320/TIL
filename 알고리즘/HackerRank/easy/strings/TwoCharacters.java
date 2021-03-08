package com.example.Algorithm.easy.strings;

//Hash set

public class TwoCharacters {
    // Complete the alternate function below.
    static int alternate(String s) {
    int answer = 0;
    String noOverlap = "";
    String result="";

    for(int i=0; i<s.length(); i++){ //글자 중복 제거
        if(!noOverlap.contains(String.valueOf(s.charAt(i)))){
            noOverlap += String.valueOf(s.charAt(i));
        }
    }

//    char[] c = new char[s.length()];

    for(int j=0; j<noOverlap.length()-1; j++){
        for(int k=j+1; k<noOverlap.length()-1; k++){
            if(s.charAt(j)==noOverlap.charAt(j) || s.charAt(k) == noOverlap.charAt(k)){
                result = s.replaceAll("[^" + s.charAt(j) + s.charAt(k) + "]", "");
            }

            if(answer > result.length()){
                answer = result.length();
            }
        }
    }




    return answer;

    }

    public static void main(String[] args){


        int l = 10;

        String s = "beabeefeab";

        int result = alternate(s);

        System.out.println(result);
    }

}
