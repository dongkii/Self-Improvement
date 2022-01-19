package sample;
import java.util.*;

public class DFS_Stack {
    
    // 방문 처리에 사용 할 배열 선언
    static boolean[] visited = new boolean[9];

    // 인덱스가 각각의 노드번호가 될 수 있게 0번 인덱스 미사용
    static int[][] graph = {{}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2}};

    // DFS 사용 할 스택
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        // 시작 노드를 스택에 넣어줌
        stack.push(1);
        
        // 시작노드를 방문 처리
        visited[1] = true;

        // 스택이 비어있지 않으면 계속 반복
        while(!stack.isEmpty()) {

            // 스택에서 하나를 꺼냄
            int nodeIndex = stack.pop();

            // 방문 노드 출력
            System.out.print(nodeIndex + " -> ");

            // 꺼낸 노드와 인접한 노드 찾기
            for(int LinkedNode : graph[nodeIndex]) {
                // 인접한 노드를 방문하지 않았을 경우에 스택에 넣고 방문처리
                if(!visited[LinkedNode]) {
                    stack.push(LinkedNode);
                    visited[LinkedNode] = true;
                }
            }
        }
    }
}
