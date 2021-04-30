package Leetcode;
//https://leetcode.com/problems/group-anagrams/
import java.util.*;

/**
 * 1. 문제 풀이 시간:
 * 2. 풀이 방법
 *   1) boolean 배열에 모두 false로 초기화, 탐색 시 애너그램이면 true로 바꿈
 *   2) false 값들 위주로
 *      2-1) checkAnagram을 통해 같은 문자들을 가지고 있는지 비교(애너그램 확인)
 *      2-2) true일때 list에 추가
 *      2-3) list를 answer 리스트에 추가, 리턴
 */

public class groupAnagram {
    public static List<List<String>> groupAnagrams(String[] strs) {

            List<List<String>> answer= new ArrayList<>();

            //초기값: 모두 false
            boolean b[]=new boolean[strs.length];
            for(int i=0;i<strs.length;i++)
            {
                List<String> list=new ArrayList<>();
                //true로 바꾸어놓으면 다시 탐색 X
                if(b[i]!=true)
                {
                    list.add(strs[i]);
                    b[i]=true;
                }

                for(int j=i+1;j<strs.length;j++)
                {
                    //두 문자열 비교해서 같으면 list에 add, b[j]는 true
                    if(b[j]!=true&&checkAnagram(strs[i],strs[j]))
                    {

                        list.add(strs[j]);
                        b[j]=true;
                    }
                }
                //해당 리스트를 answer 리스트에 추가
                if(!list.isEmpty())
                {
                    answer.add(list);
                }
            }

            return answer;

        }

        //애너그램인지 아닌지 확인
        public static boolean checkAnagram(String s1,String s2)
        {

            boolean status = true;
            //두 문자열의 크기가 같지 않으면 false
            if (s1.length() != s2.length()) {
                status = false;
            } else {
                //두 문자열을 모두 소문자로 변환, 정렬후 배열에 넣어서 비교, 같다면 true값 리턴
                char[] ArrayS1 = s1.toLowerCase().toCharArray();
                char[] ArrayS2 = s2.toLowerCase().toCharArray();
                Arrays.sort(ArrayS1);
                Arrays.sort(ArrayS2);
                status = Arrays.equals(ArrayS1, ArrayS2);
            }
            return status;
        }

    public static void main(String[] args) {

        String[] input= { "eat","tea","tan","ate","nat","bat" };

        System.out.println(groupAnagrams(input));

    }
}
