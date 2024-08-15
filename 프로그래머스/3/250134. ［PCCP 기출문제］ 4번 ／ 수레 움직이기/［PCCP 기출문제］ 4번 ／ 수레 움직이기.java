class Solution {
    
    // static int[] dx = { 0, 1, 0, -1 };
    // static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    
    static int[][] arr; // maze 배열
    static boolean visited[][][]; // 방문배열 (z=0은 빨간 수레, z=1은 파란 수레)
    static boolean redEnd; // 빨간 수레가 도착했는지 여부
    static boolean blueEnd; // 파란 수레가 도착했는지 여부
    
    static final int MAX = 999999;
    
    static class Node {
        int x;
        int y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] maze) {
        Node red = null;
        Node blue = null;
        
        arr = maze;
        // arr = new int[maze.length][maze[0].length];
        
        visited = new boolean[maze.length][maze[0].length][2];
        
        // 각 수레의 시작 위치 초기화 및 방문 처리
        for(int i=0; i<maze.length; i++) {
            for(int j=0; j<maze[i].length; j++) {
                // arr[i][j] = maze[i][j];
                if(maze[i][j] == 1) {
                    red = new Node(i, j);
                    visited[i][j][0] = true;
                } else if(maze[i][j] == 2) {
                    blue = new Node(i, j);
                    visited[i][j][1] = true;
                }
            }
        }
        
        int answer = backtracking(red, blue, 0);
        return (answer == MAX) ? 0 : answer;
    }
    
    
    static Node getNextNode(Node node, int dir) {
        int nx = node.x + dx[dir];
        int ny = node.y + dy[dir];
        
        return new Node(nx, ny);
    }
    
    
    static boolean isPossible(Node red, Node nRed, Node blue, Node nBlue) {
        // 두 수레 중 하나라도 맵의 범위를 벗어나면 false 리턴
        if(nRed.x < 0 || nRed.x >= arr.length || nRed.y < 0 || nRed.y >= arr[0].length || nBlue.x < 0 || nBlue.x >= arr.length || nBlue.y < 0 || nBlue.y >= arr[0].length) {
            return false;
        }
        
        // 두 수레 중 하나라도 벽을 만나는 경우
        if(arr[nRed.x][nRed.y] == 5 || arr[nBlue.x][nBlue.y] == 5) {
            return false;
        }
        
        // 아직 수레에 도착하지 않았는데 이미 방문한 위치라면 false 리턴
        if((!redEnd && visited[nRed.x][nRed.y][0]) || (!blueEnd && visited[nBlue.x][nBlue.y][1])) {
            return false;
        }
        
        // 두 수레가 동시에 같은 위치인 경우 false 리턴
        if(nRed.x == nBlue.x && nRed.y == nBlue.y) {
            return false;
        }
        
        // 두 수레의 위치가 스위치 됐을 경우 false 리턴
        if((nRed.x == blue.x && nRed.y == blue.y) && (nBlue.x == red.x && nBlue.y == red.y)) {
             return false;
        }
        
        return true;
    }
    
    
    // 퍼즐을 푸는데 필요한 턴의 최솟값을 구하는 백트래킹 함수
    static public int backtracking(Node red, Node blue, int count) {
        
        // 빨간 수레와 파란 수레가 모두 도착지점에 도착하면
        if(redEnd && blueEnd) {
            return count;
        }
        
        int result = MAX;
        
        // 이중 for문으로 상하좌우x상하좌우 -> 총 16가지 경우의 수 탐색
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                // 이미 도착한 수레는 다음 위치가 원래 위치
                Node nRed = (!redEnd) ? getNextNode(red, i) : red;
                Node nBlue = (!blueEnd) ? getNextNode(blue, j) : blue;
                
                // 다음 좌표가 이동 가능한 위치인지 확인하고 아니면 다음 좌표 탐색
                if(!isPossible(red, nRed, blue, nBlue)) {
                    continue;
                }
                
                visited[nRed.x][nRed.y][0] = true;
                visited[nBlue.x][nBlue.y][1] = true;
                
                // 수레가 도착했으면 true로 변경
                if(arr[nRed.x][nRed.y] == 3) redEnd = true;
                if(arr[nBlue.x][nBlue.y] == 4) blueEnd = true;
                
                result = Math.min(result, backtracking(nRed, nBlue, count+1));
                
                // 도착여부 및 방문기록 - 다시 false 처리
                redEnd = false;
                blueEnd = false;
                // if(arr[nRed.x][nRed.y] != 3) redEnd = false;
                // if(arr[nBlue.x][nBlue.y] != 4) blueEnd = false;
                visited[nRed.x][nRed.y][0] = false;
                visited[nBlue.x][nBlue.y][1] = false;
            }
        }
        return result;
    }
    
    
    
    
}