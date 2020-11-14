import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/42885

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        //배열을 오름차순으로 정렬
        Arrays.sort(people);
        
        //이 방법의 한계; 요소들이 두개 이상의 합들이 100이나 100보다 작을 때 answer로 셀 수 없다.
        //for루프를 여러 개 쓰면 효율성이 떨어진다.
//         for(int i=0; i<people.length; i++){
//            for(int j=i+1; i<people.length; j++){
//                 if(people[i] + people[j] <= limit){
//                     answer++;
//                     people[i] = 1; people[j] = 1;
//                 }
//            }
            
//         }
        
        
//         for(int k=0; k<people.length; k++){
//             if(people[k] != 1 && people[k] <= limit){
//                 answer++;
//             }
//         }
        
    //최솟값 설정
    int min = 0;

    //최댓값: people 배열의 크기 -1
    //최댓값에서 부터 limit값과 비교
    //최솟값의 위치가 최댓값의 위치보다 커지면 반복 종료
    for (int max = people.length - 1; min <= max; max--){
        //최솟값과 최댓값을 더할 때 limit보다 작다면 최솟값을 다음 값으로 변경
      if (people[min] + people[max] <= limit) {
          min++;
      }
        //if문에서 걸리지 않으면 현재 최댓값 혼자 구명보트를 탄 걸로 처리
          answer++;
      
        
      
    }
        
        return answer;
    }
}
