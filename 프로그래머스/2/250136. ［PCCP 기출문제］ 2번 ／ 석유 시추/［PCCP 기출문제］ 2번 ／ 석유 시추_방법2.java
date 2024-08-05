import java.util.*;

/**
bfs 돌 동안 y좌표를 set에 저장
set에 저장된 열에 해당하는 oil[] 배열에 석유 크기 저장
set은 bfs 돌 때마다 초기화

방문배열은 초기화x
oil[] 배열에 석유 크기 누적해서 저장
*/

class Solution {
    
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    
    static int row; // 행
    static int col; // 열
    static boolean visited[][]; // 방문배열
    static int[] oil; // 누적 석유 크기를 저장할 배열
    
    static class Node {
        int x;
        int y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] land) {
        
        row = land.length;
        col = land[0].length;
        
        visited = new boolean[row][col];
        oil = new int[col];
            
        for(int i=0; i<col; i++) {
            for(int j=0; j<row; j++) {                
                // 석유가 있고 아직 방문하지 않았다면 크기 탐색
                if(land[j][i] == 1 && !visited[j][i]) {
                    bfs(new Node(j, i), land);
                }
            }
        }
        
        Arrays.sort(oil);
        return oil[oil.length - 1];
    }
    
    static void bfs(Node node, int[][] land) {
        int count = 0; // 한번 탐색할 때 석유의 크기
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        visited[node.x][node.y] = true;
        count++;
        
        Set<Integer> set = new HashSet<>();
        set.add(node.y);
        
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            
            for(int i=0; i<4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                
                if(nx >= 0 && nx < row && ny >= 0 && ny < col) {
                    if(land[nx][ny] == 1 && !visited[nx][ny]) {
                        queue.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                        count++;
                        set.add(ny);
                    }
                }
            }
        }
        
        // set에 저장되어 있는 열에 석유 크기 저장하기
        for(int col : set) {
            oil[col] += count;
        }
    }
    
}
