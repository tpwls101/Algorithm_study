import java.util.*;

class Solution {
    
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    boolean[][] visited;
    int w, h;
    
    public int solution(int[][] maps) {
        h = maps.length; // 행(n)
        w = maps[0].length; // 열(m)
        
        visited = new boolean[h][w]; // 지도에서 방문한 곳 체크
        return bfs(0, 0, maps); // 출발점과 지도를 매개변수로
    }
    
    public int bfs(int x, int y, int[][] maps) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 1));
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            // 반복문 종료 조건
            if(node.x == h-1 && node.y == w-1) return node.cost;
            
            // 상하좌우 갈 수 있는 방향 탐색
            for(int i=0; i<4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < h && ny < w) { // 맵 안에 위치하면
                    if(maps[nx][ny] == 1 && !visited[nx][ny]) { // 맵에서 길이 있고 아직 방문하지 않았다면
                        visited[nx][ny] = true;
                        q.offer(new Node(nx, ny, node.cost + 1));
                    }
                }
            }
        }
        return -1; // while문을 통해 모든 경로를 확인했는데 도착지에 도착할 수 없을 경우 -1 반환
    }
    
    public class Node {
        int x;
        int y;
        int cost;
        
        public Node(int x, int y, int cost) { // 생성자
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
