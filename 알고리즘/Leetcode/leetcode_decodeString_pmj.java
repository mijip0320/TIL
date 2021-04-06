package Leetcode;
//https://leetcode.com/problems/decode-string/
import java.util.*;

/**
 * 1. 문제 풀이 시간: 초과
 * 2. 풀이 방법
 *   1) 스택 사용
 *   2) ]이 나오기 전까지 st에 푸쉬
 *   3) [가 아닐때 poppedChars에 [ add, [를 만나면 st에서 pop
 *   4) 해당 숫자 만큼 [과 ] 사이에 있는 문자열 반복
 */

public class decodeString {
    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        Stack<Character> st = new Stack<>();
        char[] charArray = s.toCharArray();

        for(char c : charArray){
            if(c!=']'){
                st.push(c);
            }else{

                List<Character> poppedChars = new ArrayList<>();
                while(st.peek()!='[')
                    poppedChars.add(st.pop());
                st.pop();

                int k =1;
                int num=0;
                while(!st.isEmpty() && Character.isDigit(st.peek())){
                    //숫자가 두자리 이상일때 값 구하기
                    num += k * (st.pop() - '0');
                    k=k*10;
                    //num += Integer.parseInt(String.valueOf(st.pop())); <-100[leetcode]할 때 에러 발생! 1만 인식해서 한번만 출력됨
                }

                while(num!=0){

                    for(int i=poppedChars.size()-1; i>=0;i-- )
                        st.push(poppedChars.get(i));
                    num--;
                }

            }
        }

        String res = "";
        for(Character c : st){
            res+= c;
        }

        System.out.println(res);


    }

}
