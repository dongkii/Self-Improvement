# 너비 우선 탐색 (BFS, Breadth-First Search)

**너비 우선 탐색이란**

`루트 노드(혹은 다른 임의의 노드)에서 시작해서 인접한 노드를 먼저 탐색하는 방법`

- 시작 정점으로부터 가까운 정점을 먼저 방문하고 멀리 떨어져 있는 정점을 나중에 방문하는 순회 방법이다.
- 즉, 깊게(deep) 탐색하기 전에 넓게(wide) 탐색하는 것이다.
- 사용하는 경우: **두 노드 사이의 최단경로** 혹은 **임의의 경로를 찾고 싶을 때** 이 방법을 선택한다.
    - 지구상에 존재하는 모든 친구 관계를 그래프로 표현한 후 Ash와 Vanessa 사이에 존재하는 경로를 찾는경우
    - 깊이 우선 탐색의 경우 - 모든 친구 관계를 다 살펴봐야 할지도 모른다.
    - 너비 우선 탐색의 경우 - Ash와 가까운 관계부터 탐색
- 너비 우선 탐색(BFS)이 깊이 우선 탐색(DFS)보다 좀 더 복잡하다.

**너비 우선 탐색(BFS)의 특징**
- 직관적이지 않은 면이 있다.
    - BFS는 시작 노드에서 시작해서 거리에 따라 단계별로 탐색한다고 볼 수 있다.
- BFS는 **재귀적으로 동작하지 않는다.**
- 이 알고리즘을 구현할 때 가장 큰 차이점은, 그래프 탐색의 경우 **어떤 노드를 방문했었는지 여부를 반드시 검사** 해야 한다는 것이다.
    - 이를 검사하지 않을 경우 무한루프에 빠질 위험이 있다.
- BFS는 방문한 노드들을 차례로 저장한 후 꺼낼 수 있는 자료 구조인 **큐(Queue)** 를 사용한다.
- 'Prim', 'Dijkstra' 알고리즘과 유사하다.

**너비 우선 탐색(BFS)의 과정**

깊이가 1인 모든 노드를 방문하고 나서 그 다음에는 깊이가 2인 모든 노드를, 그 다음에는 깊이가 3인 모든 노드를 방문하는 식으로 계속 방문하다가 더 이상 방문할 곳이 없으면 탐색을 마친다.

![BFS_Process](
    ../img/BFS_Process.PNG)

1. 0노드(시작 노드)를 방문한다. (방문한 노드 체크)
    - 큐에 방문된 노드를 삽입(enqueue)한다.
    - 초기 상태의 큐에는 시작 노드만이 저장
        - 즉, 0노드의 이웃 노드를 모두 방문한 다음에 이웃의 이웃들을 방문한다.
2. 큐에서 꺼낸 노드와 인접한 노드들을 모두 차례로 방문한다.
    - 큐에서 꺼낸 노드를 방문한다.
    - 큐에서 꺼낸 노드와 인접한 노드들을 모두 방문한다.
        - 인접한 노드가 없다면 큐의 앞에서 노드를 꺼낸다(dequeue)
    - 큐에 방문된 노드를 삽입(enqueue)한다.
3. 큐가 소진될 때까지 계속한다.

**너비 우선 탐색(BFS)의 구현**
- 구현 방법
    - 자료구조 큐(Queue)를 이용

**큐를 이용한 BFS 의사코드 (pseudocode)**
```java
void search(Node root) {
    Queue queue = new Queue();
    root.marked = true;     // 방문한 노드 체크
    queue.enqueue(root);    // 1-1. 큐의 끝에 추가

    // 3. 큐가 소진될 때까지 계속한다.
    while(!queue.isEmpty()) {
        Node r = queue.dequeue();   // 큐의 앞에서 노드 추출
        visit(r);       // 2-1. 큐에서 추출한 노드 방문

        // 2-2. 큐에서 꺼낸 노드와 인접한 노드들을 모두 차례로 방문한다.
        for(Node n in r.adjacent) {
            if(n.marked == false) {
                n.marked = true;        // 방문한 노드 체크
                queue.enqueue(n);       // 2-3. 큐의 끝에 추가
            }
        }
    }
}
```

**큐를 이용한 BFS 구현(JAVA)**
```java
import java.io.*;
import java.util.*;

// 인접 리스트를 이용한 방향성 있는 그래프 클래스
class Graph {
    private int V;      // 노드의 개수
    private LinkedList<Integer> adj[];  // 인접 리스트

    // 생성자
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for(int i=0; i < v; ++i)    // 인접 리스트 초기화
            adj[i] = new LinkedList();
    }

    // 노드를 연결 v -> w
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // s를 시작 노드로 한 BFS로 탐색하면서 탐색한 노드들을 출력
    void BFS(int s) {
        // 노드의 방문 여부 판단 (init : false)
        boolean visited[] = new boolean[V];

        // BFS 구현을 위한 큐(Queue) 생성
        LinkedList<Integer> queue = new LinkedList<Intger>();

        // 현재 노드를 방문한 것으로 표시하고 큐에 삽입(enqueue)
        visited[s] = true;
        queue.add(s);

        // 큐(Queue)가 빌 때까지 반복
        while(queue.size() != 0) {
            // 방문한 노드를 큐에서 추출(dequeue)하고 값을 출력
            s = queue.poll();
            System.out.print(s + " ");

            // 방문한 노드와 인접한 모든 노드를 가져온다.
            Iterator<Integer> i = adj[s].listIterator();
            while(i.hasNext()) {
                int n = i.next();
                // 방문하지 않은 노드면 방문한 것으로 표시하고 큐에 삽입(enqueue)
                if(!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}
```

```java
// 사용방법
public static void main(String args[]) {
    Graph g = new Graph(4);

    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);

    g.BFS(2);       // 주어진 노드를 시작 노드로 BFS 탐색
}
```

**`너비 우선 탐색(BFS)의 시간 복잡도`**
- 인접 리스트로 표현된 그래프: O(N+E)
- 인접 행렬로 표현된 그래프: O(N^2)
- 깊이 우선 탐색(DFS)과 마찬가지로 그래프 내에 적은 숫자의 간선만을 가지는 **희소 그래프(Sparse Graph)** 의 경우 인접 행렬보다 인접 리스트를 사용하는 것이 유리하다.

```
자료 출처 : https://gmlwjd9405.github.io/2018/08/15/algorithm-bfs.html
```