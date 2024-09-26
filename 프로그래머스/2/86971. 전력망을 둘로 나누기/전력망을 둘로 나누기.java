import java.util.*;

/**
전선을 끊는 모든 경우의 수를 탐색!!

wires를 for문 돌려서 하나씩 전선을 끊어본다. (전선 연결 상태를 저장하는 arr 배열값을 0으로 바꾼다.)
전선을 끊은 후 각 전력망의 송전탑 개수를 구한다.
하나만 구하면 나머지는 자동으로 n-cnt개
절대값의 최소값을 구한다.
다음 전선을 끊어보기 위해 원래 끊은 전선은 원상복구 시킨다. (다시 0->1로 변경)
*/

class Solution {
    
    static int n; // 송전탑의 개수
    static int[][] arr; // 전선 연결 상태를 저장할 배열
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        this.n = n;
        arr = new int[n+1][n+1];
        visited = new boolean[n+1];
        
        // 연결된 부분 1로 변경
        for(int i=0; i<wires.length; i++) {
            int from = wires[i][0];
            int to = wires[i][1];
            
            arr[from][to] = 1;
            arr[to][from] = 1;
        }
        
        // 연결된 전선 하나씩 끊어보기
        for(int i=0; i<wires.length; i++) {
            int from = wires[i][0];
            int to = wires[i][1];
            
            arr[from][to] = 0;
            arr[to][from] = 0;
            
            // 송전탑의 개수 구하기
            int cnt1 = bfs(from);
            int cnt2 = n - cnt1;
            
            answer = Math.min(answer, Math.abs(cnt1 - cnt2));
            
            // 끊어놓은 전선 원상복구
            arr[from][to] = 1;
            arr[to][from] = 1;
        }
        
        return answer;
    }
    
    // 한 전력망 안에 있는 송전탑의 개수 구하기
    static public int bfs(int num) { // num : 송전탑의 번호
        visited = new boolean[n+1]; // 방문배열 초기화
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(num);
        int count = 1;
        visited[num] = true;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int i=1; i<=n; i++) {
                if(arr[current][i] == 1 && !visited[i]) {
                    queue.add(i);
                    count++;
                    visited[i] = true;
                }
            }
        }
        
        return count;
    }
}