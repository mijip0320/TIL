# JAVA Queue 

> FIFO 형태:  그대로 먼저 들어온 데이터가 가장 먼저 나가는 구조

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbhvAPe%2FbtqHlVqf0RY%2FY4oCoA4wUkEpvIkU80i43K%2Fimg.png" style="zoom:67%;" />

- Enqueue: 큐 맨 뒤에 데이터 추가
- Dequeue: 큐 맨 앞쪽의 데이터 삭제



## 특징

1. 먼저 들어간 자료가 먼저 나오는 구조
2. 큐는 한 쪽 끝은 front로 정하여 삭제 연산만 수행
3. 다른 한 쪽 끝은 rear로 정하여 삽입 연산만 수행
4. 그래프의 넓이 우선 탐색(BFS)에서 사용
5. 컴퓨터 버퍼에서 주로 사용, 마구 입력이 되었으나 처리하지 못할 때, 버퍼(큐)를 대기 시킴



## Queue 사용법

### :yellow_heart:Queue 선언

```java
import java.util.LinkedList; //import
import java.util.Queue; //import
Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언, linkedlist 이용
Queue<String> queue = new LinkedList<>(); //String형 queue 선언, linkedlist 이용
```

- 큐는 LinkedList를 활용하여 생성

- Queue<Element> queue = new LinkedList<>()와 같이 선언



### :blue_heart:Queue 값 추가

```java
Queue<Integer> stack = new LinkedList<>(); //int형 queue 선언
queue.add(1);     // queue에 값 1 추가
queue.add(2);     // queue에 값 2 추가
queue.offer(3);   // queue에 값 3 추가
```

- 값 추가 시 **add(value)** 또는 **offer(value)** 메서드 사용

추가되는 형태:

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FLkDsr%2FbtqHnjK96ug%2F6Gh7kPASx3Qt3PE5yTOQKK%2Fimg.png" style="zoom:67%;" />



### :purple_heart:Queue 값 삭제 & 가장 먼저 들어간 값 출력

```java
Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언
queue.offer(1);     // queue에 값 1 추가
queue.offer(2);     // queue에 값 2 추가
queue.offer(3);     // queue에 값 3 추가
queue.poll();       // queue에 첫번째 값을 반환하고 제거 비어있다면 null
queue.remove();     // queue에 첫번째 값 제거
queue.clear();      // queue 초기화

Queue<Integer> q = new LinkedList<>(); //int형 queue 선언
q.offer(1);     // queue에 값 1 추가
q.offer(2);     // queue에 값 2 추가
q.offer(3);     // queue에 값 3 추가
q.peek();       // queue의 첫번째 값 참조
```

- 값을 제거하고싶다면 poll()이나 remove라는 메서드를 사용

- pop을 하면 가장 앞쪽에 있는 원소의 값이 제거됨

- 첫번째로 저장된 값 참조시 peek 사용



### :cupid:BFS 구현

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfsStudy {
    static int[][] map;
    static boolean[] visited;
    static StringTokenizer st;
    static int N,M,start,end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int m=0; m<M; m++){ //그래프 정보 입력받기
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            map[start][end] = 1;
            map[end][start] = 1;
        }

        System.out.print("그래프 BFS 방문 순서: ");
        bfs(1);
    }

    static void bfs(int point){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(point);
        visited[point] = true;

        while(!queue.isEmpty()){
            int x = queue.poll();
            System.out.print(x + " ");
            for(int i=1; i<=N; i++){
                if(map[x][i] == 1 && visited[i] == false){ //다음 정점과 연결되어 있고 아직 방문하지 않았을 때
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}

```

```
입력:
7
8
1 2
1 3
2 4
2 5
3 7
4 6
5 6
6 7

출력: 
그래프 BFS 방문 순서: 1 2 3 4 5 7 6 
```

출처: [BFS](https://javacoding.tistory.com/41)



[상하좌우 BFS 탐색](https://vmpo.tistory.com/103)