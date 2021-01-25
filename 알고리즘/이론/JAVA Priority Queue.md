# JAVA Priority Queue

> Queue: FIFO(First In First Out)의 구조

**Priority Queue**: 먼저 들어온 순서대로 데이터가 나가는 것이 아닌 우선순위를 먼저 결정하고 그 우선순위가 높은 element가 먼저 나가는 자료구조

- 우선순위 큐는 힙을 이용하여 구현하는 것이 일반적
  - 데이터를 삽입할 때 우선순위를 기준으로 최대힙 혹은 최소힙을 구성하고 데이터를 꺼낼 때 루트 노드를 얻어낸 뒤 루트노드를 삭제할 때는 빈 루트노드 위치에 맨 마지막 노드를 삽입한 후 아래로 내려가면서 적절한 자리를 찾아서 옮기는 방식



## :star2: 특징

1. 높은 우선순위의 요소를 먼저 꺼내서 처리하는 구조(큐에 들어가는 원소는 비교가 가능한 기준이 있어야 함)
2. 내부 요소는 힘으로 구성되어 이진트리 구조로 이루어져 있음
3. 내부구조가 힙으로 구성되어 있기에 시간복잡도는 O(NLogN)
4. 응급실과 같이 우선순위를 중요시해야 하는 상황에서 쓰임



## Priority Queue 사용법

### :yellow_heart:Priority Queue 선언

```java
import java.util.PriorityQueue; //import

//int형 priorityQueue 선언(우선순위가 낮은 숫자 순)
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

//int형 priorityQueue 선언(우선순위가 높은 숫자 순)
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

//String형 priorityQueue 선언(우선순위가 낮은 숫자 순)
PriorityQueue<String> priorityQueue = new PriorityQueue<>();

//String형 priorityQueue 선언(우선순위가 높은 숫자 순)
PriorityQueue<String> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
```

- 기본은 우선순위가 낮은 숫자가 부여되고 만약 높은 숫자가 우선순위가 되게 하고 싶다면 선언 시 Collections.reverseOrder() 메서드를 활용



### :blue_heart:Priority Queue 값 추가

```java
priorityQueue.add(1); //priorityQueue 값 1 추가
priorityQueue.add(2); //priorityQueue 값 2 추가
priorityQueue.offer(3); //priorityQueue 값 3 추가
```

- 우선순위 큐에 값을 추가하고 싶다면 add(value) 또는 offer(value)라는 메서드를 활용

- add 메서드의 경우 삽입에 성공하면 true 반환, 실패하면  IllegalStateException을 발생

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb52ZtE%2FbtqHjpsl7Os%2FHwdi4lWzY26XdbaEgdqEB0%2Fimg.png)



### :purple_heart:Priority Queue 값 삭제

```java
priorityQueue.poll();       // priorityQueue에 첫번째 값을 반환하고 제거 비어있다면 null
priorityQueue.remove();     // priorityQueue에 첫번째 값 제거
priorityQueue.clear();      // priorityQueue에 초기화
```

- 제거 시 poll()이나 remove()라는 메서드를 사용
  - poll() 함수는 우선순위 큐가 비어있으면 null을 반환

- 값을 제거할 시 우선순위가 가장 높은 값이 제거

- pop을 하면 가장 앞쪽에 있는 원소의 값이 제거됨
- 모든 원소 제거: clear

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbM5xqo%2FbtqHxcRRMge%2FcokOnz4QFcExmKeFnDaNW0%2Fimg.png)



### :heart:Priority Queue에서 우선순위가 가장 높은 값 출력

```java
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();//int형 priorityQueue 선언
priorityQueue.offer(2);     // priorityQueue에 값 2 추가
priorityQueue.offer(1);     // priorityQueue에 값 1 추가
priorityQueue.offer(3);     // priorityQueue에 값 3 추가
priorityQueue.peek();       // priorityQueue에 첫번째 값 참조 = 1
```

- 우선순위가 가장 높은 값을 참조하고 싶다면 peek()라는 메서드를 사용