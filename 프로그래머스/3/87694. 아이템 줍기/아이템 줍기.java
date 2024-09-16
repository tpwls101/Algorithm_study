import java.util.*;

/**
최단거리 문제는 BFS! 어렵게 생각하지 말자!
시작점 큐에 넣고 시작
동서남북 방향으로 갈 수 있는 좌표 탐색 (이게 포인트)
가능한 위치를 다시 큐에 넣고 반복

<가능한 좌표 탐색하는 방법>
1. 갈 수 있는 다음 좌표를 확인하는데 테두리가 아닌 부분을 지나갈 수 있다.
    예를 들면 예시1번에서 (3,5)에서 (3,6)으로 이동이 가능하지만 직행은 불가능하다.
    따라서 전체적인 좌표를 2배해준다.
    그러면 다음 이동 좌표가 테두리일 수 없다.
2. 직사각형이 겹칠 때 안쪽의 경로로는 가지 못하게 해야한다.
    null/true/false 또는 0/1/2로 구분
    기본 값은 0 / 이동 가능한 테두리는 1 / 이동 불가능한 안쪽은 2
    이중for문으로 직사각형 한 개의 값을 2로 채운다.
    단, 테두리면 1로 변경한다.
    다음 직사각형 이중for문을 돌린다.
    이미 2인 애들은 패스
    2가 아니라면 위와 같이 반복

<주의사항>
좌표를 2배 해줬으므로 답을 출력할 때에는 나누기 2 필수!!
*/

class Solution {
    
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };
    
    static int itemX;
    static int itemY;
    static int[][] way = new int[101][101]; // 테두리인지 안쪽 길인지를 구분해주는 배열
    static boolean[][] visited = new boolean[101][101];
    
    static class Node {
        int x;
        int y;
        int count;
        
        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        // 모든 좌표 곱하기 2 해주기
        characterX *= 2;
        characterY *= 2;
        this.itemX = itemX * 2;
        this.itemY = itemY * 2;
        
        for(int i=0; i<rectangle.length; i++) {
            for(int j=0; j<rectangle[i].length; j++) {
                rectangle[i][j] *= 2;
            }
            
            // 테두리인지 안쪽 길인지 구분
            for(int x=rectangle[i][1]; x<=rectangle[i][3]; x++) { // 행(y좌표)
                for(int y=rectangle[i][0]; y<=rectangle[i][2]; y++) { // 열(x좌표)
                    if(way[x][y] == 2) continue;
                    way[x][y] = 2;
                    // 테두리면 1로 변경
                    if(x == rectangle[i][1] || x == rectangle[i][3] || y == rectangle[i][0] || y == rectangle[i][2]) {
                        way[x][y] = 1;
                    }
                }
            }
        }
        
        // way 배열 확인
        // for(int i=50; i>=0; i--) {
        //     for(int j=0; j<=50; j++) {
        //         System.out.print(way[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        answer = bfs(new Node(characterY, characterX, 0)); // 행과 열 순서 + 이동한 거리
        
        return answer / 2;
    }
    
    static public int bfs(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        visited[node.x][node.y] = true;
        
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            
            if(current.x == itemY && current.y == itemX) {
                return current.count;
            }
            
            for(int i=0; i<4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                
                if(nx >= 0 && nx <= 100 && ny >= 0 && ny <= 100) {
                    if(way[nx][ny] == 1 && !visited[nx][ny]) {
                        queue.add(new Node(nx, ny, current.count+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return 0;
    }
    
}