import java.util.*;

/**
그래프를 인접리스트로 미리 구현해두면
정점에서 연결된 정점만 바로 확인해 큐에 추가할 수 있다.
*/

class Solution {
    
    static boolean[] visited;
    static int dist = 0; // 현재 1번 노드로부터 떨어진 거리
    static int count = 0; // 1번 노드로부터 가장 멀리 떨어진 노드의 개수
    
    static class Node {
        int num; // 노드 번호
        int cnt; // 1번 노드에서 num 노드까지의 최단 거리
        
        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    
    public int solution(int n, int[][] edge) {
        visited = new boolean[n+1];
        
        // 그래프를 인접리스트로 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        for(int i=0; i<=edge.length; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        for(int[] e : edge) {
            int from = e[0];
            int to = e[1];
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(1, 0));
        visited[1] = true;
        
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            
            // 1번 노드로부터 떨어진 거리가 바뀌면 개수 초기화
            if(current.cnt != dist) {
                count = 0;
                dist = current.cnt;
            }
            
            count++;
            
            // 현재 노드와 연결된 노드 큐에 추가
            for(int i=0; i<graph.get(current.num).size(); i++) {
                int nodeNum = graph.get(current.num).get(i);
                
                if(!visited[nodeNum]) {
                    queue.add(new Node(nodeNum, current.cnt + 1));
                    visited[nodeNum] = true;
                }
            }
        }
        
        return count;
    }
}
