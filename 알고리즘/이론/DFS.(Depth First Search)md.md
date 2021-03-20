# DFS

> Root Node에서 다음 브랜티로 넘어가기 전에 해당 분기를 완벽하게 탐색하는 방법

stack 아님 재귀함수로 구현<br>

- 경로 탐색 시 한 방향으로 진행, 더 이상 갈 수 없으면 다른 방향으로 다시 탐색
- 모든 노드를 방문해야 하는 경우 이 방법 사용

시간 복잡도<br>

- 인접리스트: O(V+E)
- 인접 행렬: O(V^2)
- 접점: V, 간선: E

![](https://upload.wikimedia.org/wikipedia/commons/7/7f/Depth-First-Search.gif)

```java
/* 인접 리스트 이용 */
class Graph {
  private int V;
  private LinkedList<Integer> adj[];
 
  Graph(int v) {
      V = v;
      adj = new LinkedList[v];
      // 인접 리스트 초기화
      for (int i=0; i<v; ++i)
          adj[i] = new LinkedList();
  }
  void addEdge(int v, int w) { adj[v].add(w); }
 
  /* DFS에 의해 사용되는 함수 */
  void DFSUtil(int v, boolean visited[]) {
      // 현재 노드를 방문한 것으로 표시하고 값을 출력
      visited[v] = true;
      System.out.print(v + " ");
 
      // 방문한 노드와 인접한 모든 노드를 가져온다.
      Iterator<Integer> it = adj[v].listIterator();
      while (it.hasNext()) {
          int n = it.next();
          // 방문하지 않은 노드면 해당 노드를 시작 노드로 다시 DFSUtil 호출
          if (!visited[n])
              DFSUtil(n, visited);
      }
  }
 
  /* DFS */
  void DFS(int v) {
      boolean visited[] = new boolean[V];
 
      // v를 시작 노드로 DFSUtil 재귀 호출
      DFSUtil(v, visited);
  }
}
```

```java
// dfs, 재귀, 인접 행렬, i 정점부터 시작한다. 
public static void dfs(int i) { 
	visit[i] = true; 
	System.out.print(i + " "); 
	for(int j=1; j<n+1; j++) { 
        if(map[i][j] == 1 && visit[j] == false) { 
            dfs(j); 
        } 
    } 
}
```

