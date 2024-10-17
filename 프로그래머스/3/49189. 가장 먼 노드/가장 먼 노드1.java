import java.util.*;

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
            
            for(int i=0; i<edge.length; i++) {
                // 현재 노드와 연결된 노드
                int from = edge[i][0];
                int to = edge[i][1];
                
                if(from == current.num && !visited[to]) {
                    queue.add(new Node(to, current.cnt + 1));
                    visited[to] = true;
                } else if (to == current.num && !visited[from]) {
                    queue.add(new Node(from, current.cnt + 1));
                    visited[from] = true;
                }
            }
        }
        
        return count;
    }
}
