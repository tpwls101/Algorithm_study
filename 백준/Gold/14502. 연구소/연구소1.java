import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_14502_연구소>
 * 벽을 세우는 모든 경우의 수 -> DFS
 * 벽 세우면 바이러스 퍼트리기 -> BFS
 * 
 * 주의 !! -> 깊은 복사 사용할 것
 * 
 * @author 유세진
 *
 */

public class Main {
    
    public static int N; // NxM 연구소
    public static int M; // NxM 연구소
    public static int[][] map;
    public static int max = Integer.MIN_VALUE; // 안전영역 크기의 최대값
    
    public static int[] dx = { 0, 1, 0, -1 };
    public static int[] dy = { 1, 0, -1, 0 };    
    
    // 바이러스의 x, y 좌표를 저장하기 위한 클래스
    public static class Node {
    	int x;
    	int y;
    	
    	Node(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M]; // NxM 연구소 (N, M : 3 ~ 8)
        
        // 연구소 상태 입력받기
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0);
        
        System.out.println(max);
    }
    
    // 3개의 벽을 세울 수 있는 모든 경우의 수 탐색
    // cnt : 현재 세운 벽의 수
    private static void dfs(int cnt) {
    	if(cnt == 3) { // 벽을 3개 세우면
    		bfs(); // 바이러스 퍼트리기
    		return;
    	}
    	
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1; // 벽 세우기
                    dfs(cnt+1);
                    map[i][j] = 0; // 경우의 수 확인하면 다시 벽 없애고 원래대로
                }
            }
        }
    }
    
    // 바이러스 퍼트리기
    private static void bfs() {
    	// 퍼진 바이러스를 확인하기 위해 복사한 새로운 배열 사용
    	int[][] copy = new int[N][M];
    	// copy = map.clone(); // X
    	
    	for(int i=0; i<N; i++) {
    		copy[i] = map[i].clone();
    	}
    	
    	Queue<Node> queue = new ArrayDeque<>();
    	
    	// 연구소에서 바이러스가 있는 곳만 큐에 삽입
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			if(map[i][j] == 2) {
    				queue.add(new Node(i, j));
    			}
    		}
    	}
    	
    	while(!queue.isEmpty()) {
    		Node current = queue.poll(); // 현재 노드
    		
    		for(int i=0; i<4; i++) {
    			int temp_x = current.x + dx[i];
    			int temp_y = current.y + dy[i];
    			
    			// 연구소 범위 안이고
    			if(temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < M) {
    				if(copy[temp_x][temp_y] == 0) { // 빈 공간이면
    					queue.add(new Node(temp_x, temp_y));
    					copy[temp_x][temp_y] = 2; // 바이러스 전파
    				}
    			}
    		}
    	}
    	
    	// 안전 지역 확인
    	int count = 0;
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			if(copy[i][j] == 0) {
    				count++;
    			}
    		}
    	}
    	
    	max = Math.max(max, count); // 안전영역 크기의 최댓값 갱신
	}

}
