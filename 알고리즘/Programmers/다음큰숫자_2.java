package Programmers;
import java.util.*;
//https://programmers.co.kr/learn/courses/30/lessons/12911


/**
 * 1. 문제풀이시간: 30분
 * 2. 컴퓨팅사고
 *  2-1) n을 이진수로 바꿔 1의 갯수를 셈(count1)
 *  2-2) n이 1000000 이하일 때
 *      2-2-1) n에서 1씩 증가, 이진수로 바꿔서 1의 갯수를 셈(count2)
 *      2-2-2) 이때 count1과 count2를 비교해서 숫자가 같으면 해당 숫자의 십진수 형태(m)을 answer로 리턴
 *      2-2-3) count1과 count2가 같지 않다면 count2를 다시 0으로 초기화, 다시 m을 1씩 증가(n+=1)해서 실행,비교 반복
 */
public class NextBiggestNumber {
    public static int solution(int n) {
        int answer = 0;
        int count1 = 0; //n의 1을 셈
        int count2 = 0; //m의 1을
        String nBinary = Integer.toBinaryString(n);
        for(int i=0; i<nBinary.length(); i++){
            if(nBinary.charAt(i) == '1'){
                count1+=1;
            }
        }

        while(n<=1000000){
            int m = ++n;
            String mBinary = Integer.toBinaryString(m);
            for(int j=0; j<mBinary.length(); j++){
                if(mBinary.charAt(j) == '1'){
                    count2+=1;
                }
            }
            if(count1 == count2){
                answer = m;
                break;
            }else{
                count2 = 0;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 78;
        int result  = solution(n);
        System.out.println(result);
    }
}
