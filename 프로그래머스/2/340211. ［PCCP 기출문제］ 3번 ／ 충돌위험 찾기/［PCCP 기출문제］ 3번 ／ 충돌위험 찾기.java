import java.util.*;

/**
어려웠던 문제
특히 최단경로를 구할 때 BFS를 사용할 필요가 없다는 점
(왜냐, BFS로 최단경로를 구한다해도 위아래 먼저 움직이는 것은 어떻게 처리해줄 것이며,
굳이 BFS를 활용하지 않아도 x좌표를 먼저 다음 목적지까지 이동시키고 y좌표를 이동시키면 그게 최단경로다.)
그리고 큐 타입의 배열을 만들어 각 로봇마다 모든 경로를 저장한다는 점 -> 생각하지 못한 부분
그리고 배열의 모든 인덱스에서 좌표를 하나씩 뽑아 같은 위치인지 확인하면 된다.
*/

class Solution {
    
    static int[][] points;
    static int[][] routes;
    static Queue<Node>[] record; // 로봇의 최단경로를 큐로 저장한 배열
    static int N; // 로봇의 개수
    static int answer = 0; // 위험한 상황 횟수
    
    static class Node {
        int x;
        int y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        this.points = points;
        this.routes = routes;
        
        N = routes.length;
        record = new LinkedList[N];
        
        // record 배열 초기화
        for(int i=0; i<N; i++) {
            record[i] = new LinkedList<>();
        }
        
        // 로봇의 최단거리 이동 경로 저장
        recording();
        
        // 충돌 위험 세기
        countCollision();
        
        return answer;
    }
    
    // 로봇의 최단거리 이동 경로를 저장하는 함수
    static void recording() {
        for(int i=0; i<N; i++) {
            // 로봇의 시작 위치 좌표
            int robotNum = routes[i][0];
            int x = points[robotNum-1][0];
            int y = points[robotNum-1][1];
            
            // 로봇의 시작 위치를 큐에 추가
            record[i].add(new Node(x, y));
            
            // 로봇의 경로에 따라 다음 포인트로 이동
            for(int j=1; j<routes[i].length; j++) {
                int nextRobotNum = routes[i][j];
                int nx = points[nextRobotNum-1][0];
                int ny = points[nextRobotNum-1][1];
                
                // 위아래로 먼저 이동하고 좌우로 이동한다
                while(x != nx) { // 위아래(행)
                    if(x < nx) x++;
                    else x--;
                    record[i].add(new Node(x, y)); // 이동한 좌표를 큐에 기록
                }
                
                while(y != ny) { // 좌우(열)
                    if(y < ny) y++;
                    else y--;
                    record[i].add(new Node(x, y));
                }
            }
        }
    }
    
    // 충돌 위험을 세는 함수
    static void countCollision() {
        int count = 0; // 경로가 끝난 로봇의 수
        
        // 모든 로봇의 경로가 끝날 때까지 반복
        while(count < N) {
            int[][] map = new int[101][101]; // 같은 시간에 좌표에 몇 개의 로봇이 지나는지 저장
            count = 0; // 초기화
            
            // 같은 시간일 때 각 로봇의 위치를 맵에 기록
            for(int i=0; i<N; i++) {
                if(record[i].isEmpty()) {
                    count++; // 경로가 끝난 로봇의 수 증가
                    continue;
                }
                
                Node current = record[i].poll();
                map[current.x][current.y]++;
            }
            
            // 맵에서 두 개 이상의 로봇이 지나갔으면 카운트
            for(int i=0; i<101; i++) {
                for(int j=0; j<101; j++) {
                    if(map[i][j] > 1) answer++;
                }
            }
        }
    }
}
