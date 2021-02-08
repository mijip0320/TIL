# BFS(Breadth First Search)

> 그래프 넓이우선탐색 알고리즘

![](https://t1.daumcdn.net/cfile/tistory/246A414458C2B3BF25)

- 1번부터 방문 시작, 2,3,4순으로 모든 정점들을 하나씩 다 방문

- 처음 시작 정점을 방문한 뒤, 정점에서 인접한 모든 정점들을 우선 방문
- 방문하지 않은 정점이 없어질 때까지 다른 모든 정점들에 대해서 같은 방식으로 우선 탐색
- 선입선출 원칙, Queue 사용



### [1]방문, 방문순서: 1

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile6.uf.tistory.com%2Fimage%2F265F7F3458C2B50114BCC4)

- 1번 노드에서 출발, 1번 노드와 인접한 노드인 2,3을 큐에 넣음
- 맨 위에 위치한 2번으로 현재위치 변경, 2번 노드는 큐에서 뺌



### [2], 1-2

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile5.uf.tistory.com%2Fimage%2F2759843958C2B4EA02F779)

- 현재위치: 2번 노드, 2번노드와 인접한 노드인 4번과 5번을 새로 큐에 넣음
- 맨위에 있는 3번 정점으로 이동, 3번을 큐에서 뺌



### [3], 1-2-3

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile28.uf.tistory.com%2Fimage%2F264F963858C2B4DF381083)

- 3번 정점으로 이동, 인접한 정점들인 6과 7을 큐에 삽입
- 맨위에 있는 4가 정점이 되면서 큐에서 뺌



### [4], 1-2-3-4

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile5.uf.tistory.com%2Fimage%2F2406BD3658C2B5670F3203)

- 4번 정점으로 이동, 인접한 8번 노드를 큐에 삽입
- 5번 정점으로 이동하면서 큐에서 뺌



### [5], 1-2-3-4-5

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile28.uf.tistory.com%2Fimage%2F243B7B3858C2B5A8156CA2)

- 5번 정점으로 이동하고 나니 **더 이상 새롭게 추가할 인접한 정점 X**
- 큐에서 가장 먼저 들어온 6번 정점으로 이동, 큐에서 뺌



### [6], 1-2-3-4-5-6

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile28.uf.tistory.com%2Fimage%2F2234D53A58C2B5F8299784)

### [7], 1-2-3-4-5-6-7

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile21.uf.tistory.com%2Fimage%2F2448243A58C2B6150183F3)



### [8], 1-2-3-4-5-6-7-8

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile5.uf.tistory.com%2Fimage%2F255D093558C2B62439DB69)



- **더이상 Queue에 남아있는 정점이 없을 때, 탐색 종료**
- 빅오 O(V+E)의 시간 복잡도를 가짐



출처: [BFS](https://www.leafcats.com/108)



