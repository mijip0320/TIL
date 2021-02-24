package com.example.Algorithm.easy.strings;

public class StrongPassword {

    //password has to satisfy the following criteria:
//    Its length is at least 6
//    It contains at least one digit.
//    It contains at least one lowercase English character.
//    It contains at least one uppercase English character.
//    It contains at least one special character. The special characters are: !@#$%^&*()-+

    // Complete the minimumNumber function below.
    static int minimumNumber(int n, String password) {
        int answer = 0;

        boolean flag1 = false; //대문자 체크
        boolean flag2 = false; //소문자 체크
        boolean flag3 = false; //숫자 체크
        boolean flag4 = false; //특수문자 체크

            for(int i=0; i<password.length(); i++){
                //각 조건들에 대한 검사 실시(조건에 충족한다면 flag들을 true로 바꿈) -> answer++안하게 됨!
                    if(Character.isUpperCase(password.charAt(i))){
                        flag1 = true;
                    }else if (Character.isLowerCase(password.charAt(i))){
                        flag2 = true;
                    }else if(Character.isDigit(password.charAt(i)) ){
                        flag3 = true;
                    }else if(!password.matches("[0-6|a-z|A-Z|]*")){
                        flag4 = true;
                    }

            }

            //각 flag들이 false 일때 answer 증가(필요한 문자 개수)
        if(!flag1){
                answer++;
            }
        if(!flag2){
            answer++;
        }
        if(!flag3){
            answer++;
        }
        if(!flag4){
            answer++;
        }


        //최소값(6)에서 password 길이를 뺀 값이 answer보다 작다면 answer 출력, 아니라면 뺀 값 출력
        return 6-n < answer? answer : 6-n;
    }


    public static void main(String[] args){

        int n = 11;
        String password = "#HackerRank";

        int answer = minimumNumber(n, password);

        System.out.println(answer);
    }
}
