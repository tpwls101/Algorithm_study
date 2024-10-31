import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_14940_쉬운최단거리> - 두 번째 풀이
 * 1. 첫 번째 풀이
 * 이중 for문 돌리면서 각 위치마다 bfs 탐색
 * 메모리 초과난다.
 * 
 * 2. 두 번째 풀이
 * 겹치는 부분을 줄이기 위해 목표지점(2)에서 시작해 사방탐색을 하며 각 좌표에 값을 넣는다.
 * 처음에는 answer[]을 전부 -1로 초기화하고, bfs 돌면서 1은 최단거리로 0은 0으로 바꿔줬는데
 * 왜 틀리는건지 이유를 모르겠다............
 * 그냥 처음 입력값 받을 때 0 처리 해주고 방문 안한 곳만 -1 처리 해줘버려.......
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 세로의 크기
	static int M; // 가로의 크기
	static int[][] arr;
	static int[][] answer;
	static boolean[][] visited;
	static int startX; // 시작 x좌표
	static int startY; // 시작 y좌표
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	static class Node {
		int x;
		int y;
		int cnt; // (x,y) 좌표에서 목표지점까지의 거리
		
		Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
		arr = new int[N][M];
		answer = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(arr[i][j] == 2) {
					startX = i;
					startY = j;
				}
				
				if(arr[i][j] == 0) {
					answer[i][j] = 0;
					visited[i][j] = true;
				}
			}
		}
		
		bfs(new Node(startX, startY, 0));
		
		// 0 1 2인 칸 모두 방문 처리 해줬음
		// 도달하지 못한 곳만 방문 처리 안되어있으니 -1로 바꿔주면 된다
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j]) {
					answer[i][j] = -1;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	// 0이나 2인 칸은 이미 처리했음. 1인 칸만 answer[] 배열에 값 바꿔주면 됨
	static void bfs(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		visited[node.x][node.y] = true;
		answer[node.x][node.y] = 0;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if(!visited[nx][ny] && arr[nx][ny] == 1) {
						answer[nx][ny] = current.cnt + 1;
						queue.add(new Node(nx, ny, current.cnt + 1));
						visited[nx][ny] = true;
					}
				}
			}
		}
	}

}