package Programmers;
//https://programmers.co.kr/learn/courses/30/lessons/12979?language=java
public class baseStation {

    public static void main(String[] args) {
        int n = 11;
        int[] stations = {4, 11};
        int w = 1;
        int answer = 0; //기지국 개
        int curIdx = 0; //기지국 인덱가
        int apartment = 1; // 아파트 1동

        //  아파트 전체 돌
        while (apartment <= n) {
            //1. 기지국이 설치된(stations배열) 곳까지만 탐색(체크해보기)
            //2. 현재 아파트가 기존 설치된 기지국(stations[curIdx]의 전파범위(w)안에 존재하는지 확인
            //3. 만약 기존 설치된 기지국 범위 안에 있을 경우 =>전파 범위안에 포함되므로 설치 X
            if (curIdx < stations.length && apartment >= stations[curIdx] - w) {
                //다음 이동해야 할 아파트는
                apartment = stations[curIdx] + w + 1; //기지국 오른쪽 끝으로 이동
                curIdx++; //다음 설치한 기지국을 찾기 위해 기지국 인덱스 증
            } else {
                answer += 1; //기지국 추가 설치

                //기지국 설치에 의해 발생하는 최대 전파 범위
                //왼쪽 전파범위 + 기지국설치(1) + 오른쪽 전파범위
                apartment += (w * 2) + 1;
            }


        }

        System.out.println(answer);


    }
}