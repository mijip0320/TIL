package com.algorithm.easy.implementation;

//test case 모두 통과 X

public class DayOfTheProgrammer {
    // Complete the dayOfProgrammer function below.
    static String dayOfProgrammer(int year) {
//    boolean leapYear = false;
        int month = 0;
        int day = 0;
        String answer = "";
        int[] months= {31,28,31,30,31,30,31,31,30,31,30,31};

        if(year >= 1700 && year <= 2700){
            if(year % 4 == 0){
//        leapYear = true;
                months[1] = 29;
            }else{
//        leapYear = false;
                months[1] = 28;
            }
        }



        int sum = 0;
        for(int i=0; i<=months.length; i++){
//        System.out.println(months[i]);
            if(sum < 256){
                sum += months[i];
                month++;
            }else if(sum == 256){
                break;
            }else{
                sum -= months[i-1];
                break;
            }
        }
        String ansmonth="";
        if(month < 10){
            ansmonth = "0"+Integer.toString(month);
        }
//        System.out.println(sum);
        day = 256 - sum;
        answer = day+"."+ansmonth+"."+year;
        return answer;
    }

    public static void main(String[] args) {
//        int year = 2017;
        int year = 2100;
        String result = dayOfProgrammer(year);

        System.out.println(result);

    }

}