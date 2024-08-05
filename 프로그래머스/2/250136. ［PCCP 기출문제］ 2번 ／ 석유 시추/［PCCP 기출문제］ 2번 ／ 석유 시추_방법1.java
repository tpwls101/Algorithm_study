import java.util.*;

/**
<방법1>
정확성은 제한이 100까지라 통과했지만
효율성은 통과x (제한 500까지 -> 시간초과!)

석유에 해당되는 열을 저장함으로써 bfs가 반복적으로 실행되는 것을 막아야 한다!
*/

class Solution {
    
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    
    static int row; // 행
    static int col; // 열
    static boolean visited[][]; // 방문배열
    static int size = 0; // 석유의 크기
    
    static class Node {
        int x;
        int y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        
        row = land.length;
        col = land[0].length;
            
        for(int i=0; i<col; i++) {
            // 매 열마다 방문배열 초기화 필요
            visited = new boolean[row][col];
            
            int sum = 0; // 한 열을 뚫을 때의 석유의 합
            for(int j=0; j<row; j++) {                
                // 석유가 있고 아직 방문하지 않았다면 크기 탐색
                if(land[j][i] == 1 && !visited[j][i]) {
                    sum += bfs(new Node(j, i), land);
                }
            }
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
    
    static int bfs(Node node, int[][] land) {
        int count = 0; // 한번 탐색할 때 석유의 크기
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        visited[node.x][node.y] = true;
        count++;
        
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
                    }
                }
            }
        }
        
        return count;
    }
}
