// https://programmers.co.kr/learn/courses/30/lessons/43163
//bfs 형식
import java.util.*;

class Solution {
    static class Node{ //현재 값에 대한 노드 생성
        String next; //현재 문자열 값
        int edge; //해당 값의 노드
        
        public Node(String next, int edge){
            this.next = next;
            this.edge = edge;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int n = words.length;
        
        Queue<Node> q = new LinkedList<>();
        
        boolean[] visit = new boolean[n];
        q.add(new Node(begin, 0)); //루트값을 큐에 대입
        
        while(!q.isEmpty()){
            Node current = q.poll(); //큐에 있는 루트값을 current에 대입
            if(current.next.equals(target)){ //current 값이 target 값이면 바로 리턴, break
                answer = current.edge;
                break;
            }
            
            for(int i=0; i<words.length;i++){ //아닐 경우 bfs 탐색 시작
                if(!visit[i] && check(current.next, words[i])){ //아직 방문하지 않았고 다음 단어와 한글자만 차이 난다면
                    visit[i] = true; //방문 여부 표시
                    q.add(new Node(words[i], current.edge+1)); //같은 레벨에 있다는 것이기 때문에 큐에 add
                }
            }
        }
        
            
            
        return answer;
    }
    public static boolean check(String cur, String n){ 
        int cnt = 0;
        for(int i=0; i<n.length(); i++){
            if(cur.charAt(i) != n.charAt(i)){ 
                if(++ cnt > 1) return false; //false를 두번 만나게 되면 false가 리턴됨(글자가 두개 이상 다를 때)
            }
        }
        
        return true; //글자가 하나만 다를 때 true 리턴
    }
    
}
/*출력값: 
*current.edge2: 0 words[i]: hot
current.edge2: 1 words[i]: dot
current.edge2: 1 words[i]: lot
current.edge2: 2 words[i]: dog
current.edge2: 2 words[i]: log
current.edge2: 3 words[i]: cog
current.edge1: 4
4

*
/