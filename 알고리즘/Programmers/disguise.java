import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/42578?language=java
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1; 

		//{옷 종류}:{총 개수} 와 같은 형태로 매칭되어야 하므로, Key:Value 형태로 설정
    	HashMap<String, Integer> map = new HashMap<>();
    	
    	//2개의 옷종류와 그 옷이 {a, b} 개수로 있을 때
    	//(a+1) * (b+1) -1의 경우의 수로 조합할 수 있음
    	//최소 하나는 입어야 하기 때문에 아무것도 입지 않는 선택지는 제외
    	//(하나를 입거나 안입는 선택지) * (하나를 입거나 안입는 선택지) -(아무것도 안 입는 선택지)
    	
    	for(int i = 0; i < clothes.length; i++) {
    		if(map.get(clothes[i][1]) == null) {
    			map.put(clothes[i][1], 1);
    		
    		}else {
    			map.put(clothes[i][1], map.get(clothes[i][1]) + 1);
    	
    		}
    			
    	}
    	
    	
    	
    	for(String keys: map.keySet()) {
    		//(a+1) * (b+1) *...
    		answer *= (map.get(keys) + 1);
    	
    	}
        
    	answer -= 1;
        return answer;
    }
}
