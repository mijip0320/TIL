# JAVA Stack

> 스택의 가장 큰 특징: LIFO(Last In First Out)의 형태

- 시스템 해킹에서 버퍼오버플로우 취약점을 이용한 공격을 할 때 스택 메모리의 영역에서 함
- 인터럽트 처리, 수식의 계산, 서브루틴의 복귀 번지 저장 등에 쓰임
- 그래프의 깊이 우선 탐색(DFS)에서 사용
- 재귀적(Recursion) 함수를 호출할 때 사용



### Stack 선언:yellow_heart:

```java
import java.util.Stack; //import
Stack<Integer> stack = new Stack<>(); //int형 스택 선언
Stack<String> stack = new Stack<>(); //char형 스택 선언
```

- Stack<Element> stack = new Stack<>();과 같은 형식으로 선언



### Stack 값 추가:blue_heart:

```java
Stack<Integer> stack = new Stack<>(); //int형 스택 선언
stack.push(1); //stack에 값 1 추가
stack.push(2); //stack에 값 2 추가
stack.push(3); //stack에 값 3 추가
```



### Stack 값 삭제:purple_heart:

```java
Stack<Integer> stack = new Stack<>(); //int형 스택 선언
stack.push(1);     // stack에 값 1 추가
stack.push(2);     // stack에 값 2 추가
stack.push(3);     // stack에 값 3 추가
stack.pop();       // stack에 값 제거
stack.clear();     // stack의 전체 값 제거 (초기화)
```



### Stack의 가장 상단의 값 출력:heart:

```java
Stack<Integer> stack = new Stack<>(); //int형 스택 선언
stack.push(1);     // stack에 값 1 추가
stack.push(2);     // stack에 값 2 추가
stack.push(3);     // stack에 값 3 추가
stack.peek();     // stack의 가장 상단의 값 출력
```

- 스택의 가장 위에 있는 값을 출력 : peek()



### Stack의 기타 메서드:green_heart:

```java
Stack<Integer> stack = new Stack<>(); //int형 스택 선언
stack.push(1);     // stack에 값 1 추가
stack.push(2);     // stack에 값 2 추가
stack.size();      // stack의 크기 출력 : 2
stack.empty();     // stack이 비어있는제 check (비어있다면 true)
stack.contains(1) // stack에 1이 있는지 check (있다면 true)
```

