import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_14503_로봇청소기>
 * 0 : 청소되지 않은 칸 / 1 : 벽 / 2 : 청소한 칸
 * 
 * 현재 위치를 청소한 후, 반시계 방향으로 돌면서 한 곳이라도 청소하지 않은 곳이 있다면 그 방향으로 청소 진행
 * 4군데 모두 청소할 수 없다면 방향 유지한 채 뒤로 후진
 * 다시 반복
 * 
 * dfs로 풀 때는 count가 1로 시작함 주의
 * 이미 첫 출발 지점을 청소했으므로!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static int dir; // 방향
	static int[][] arr;
	static int count = 1; // 로봇 청소기가 청소한 칸의 개수
	
	// 상우하좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	static class Node {
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
		
		st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(new Node(x, y));
		
		System.out.println(count);
	}
	
	static void dfs(Node node) {
		arr[node.x][node.y] = 2; // 현재 칸 청소
		
		for(int i=0; i<4; i++) {
			int nx = node.x + dx[(dir+3-i) % 4];
			int ny = node.y + dy[(dir+3-i) % 4];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < M) { // 범위를 벗어나지 않고
				if(arr[nx][ny] == 0) { // 청소할 수 있다면
					dir = (dir+3-i) % 4; // 방향 변경
					arr[nx][ny] = 2; // 청소
					count++;
					
					// 다른 방향 청소가 아니라 현재 위치에서 다시 4군데 탐색해야 함
					dfs(new Node(nx, ny));
					
					// 여기서 return을 안하면 다른 방향으로 청소를 할 수 있음
					return;
				}
			}
		}
		
		// 4군데 모두 청소할 수 없다면, 방향 유지한 채로 뒤로 한 칸 후진
		int nx = node.x + dx[(dir+2) % 4];
		int ny = node.y + dy[(dir+2) % 4];
		
		if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
			// 벽이 있어 후진할 수 없다면 작동을 멈춘다
			if(arr[nx][ny] == 1) {
				return;
			}
			// 그게 아니라면 후진한 위치에서 다시 청소 진행
			else {
				dfs(new Node(nx, ny));
			}
		}
		
	}
}
