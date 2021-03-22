package programmers;
//https://programmers.co.kr/learn/courses/30/lessons/42860?language=java

public class joyStick2 {
    public static int solution(String name) {
        int answer = 0;
        char[] temp = new char[name.length()];
        //최대로 가질 수 있는 min값은 끝까지 가는것
        int min_move = temp.length-1;

        for(int i=0; i<name.length(); i++){
            temp[i] = name.charAt(i);
        }

        for(int j=0; j< temp.length; j++){
            //1. 위아래 움직임에 대한 조건문
            //78은 뒤에서 빼거나 앞에서 빼도 똑같이 13이 나옴, 고로 어디든 계산 가능
            if(temp[j] >= 78){
                //Z으로 우선 이동해야 하기 때문에 추가적으로 +1
                  answer += 90 - temp[j]+1;
             }else if(temp[j]<78){
                //현재 위치에서 A를 뺌
                        answer+= temp[j] -65;
              }
//            더 간단한 코드: answer += Math.min(temp[j]-'A', 'Z'-temp[j]+1);
            //2. 좌 우로 움직이는 경우
            //2-1. 처음부터 순차적으로(우측으로) 가는 경우(ex) JEROEN) => 이 경우 전체 글자의 길이 -1을 해주면 됨
            //2-2. 뒤부터 가는 경우: JAAAAAAIN => 순차이동은 오른쪽으로 8번 이동, 좌측이동은 J->N->I순으로 이동하여 위 아래만 조작하면 됨
            //2-3. 연속된 A의 등장: BBAAAAABA => min_move와 비교해주어야 하는 값은 현재 위치에서 첫 위치로 돌아간 후(j+j) A가 연속으로 나오는 지점의 다음(next)을 끝(temp.length)에서 계산해 주는 것이다 -> temp.length-next
            int next = j+1;
            while(next<temp.length && temp[next] == 'A'){
                next++;
            }
            min_move = Math.min(min_move, j+temp.length-next + j);
        }



        answer += min_move;
        return answer;
    }

    public static void main(String[] args) {
        String name = "BBAAAAABA";
        System.out.println(solution(name));
    }
}

//https://taesan94.tistory.com/38