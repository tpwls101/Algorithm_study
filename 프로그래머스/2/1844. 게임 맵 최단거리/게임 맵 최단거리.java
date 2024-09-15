import java.util.*;

class Solution {
    
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    
    static int[][] maps;
    static boolean[][] visited;
    
    static class Node {
        int x;
        int y;
        int count;
        
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    public int solution(int[][] maps) {
        this.maps = maps;
        visited = new boolean[maps.length][maps[0].length];
        
        return bfs(new Node(0, 0, 1));
    }
    
    static public int bfs(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        visited[node.x][node.y] = true;
        
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            if(current.x == maps.length-1 && current.y == maps[0].length-1) {
                return current.count;
            }
            
            for(int i=0; i<4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                
                if(nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length) {
                    if(maps[nx][ny] == 1 && !visited[nx][ny]) {
                        queue.add(new Node(nx, ny, current.count+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        
        return -1;
    }
    
}