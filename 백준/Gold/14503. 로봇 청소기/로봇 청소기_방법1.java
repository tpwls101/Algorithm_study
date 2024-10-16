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
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static int dir; // 방향
	static int[][] arr;
	static int count = 0; // 로봇 청소기가 청소한 칸의 개수
	
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
		
		clean(new Node(x, y));
		
		System.out.println(count);
	}
	
	static void clean(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			// 현재 칸이 아직 청소되지 않았다면 청소
			if(arr[current.x][current.y] == 0) {
				arr[current.x][current.y] = 2;
				count++;
			}
			
			boolean state = false; // 현재 칸의 주변 4칸 중 청소할 수 있는가?
			
			for(int i=0; i<4; i++) {
				// 현재 방향에서 반시계 방향으로 90도 회전한 방향부터 탐색
				int nx = current.x + dx[(dir+3-i) % 4];
				int ny = current.y + dy[(dir+3-i) % 4];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) { // 범위를 벗어나지 않고
					if(arr[nx][ny] == 0) { // 청소되지 않은 칸이라면
						state = true; // 청소할 수 있는 상태로 변경하고 청소
						dir = (dir+3-i) % 4; // 방향은 반시계 방향으로 90도 회전
						queue.add(new Node(nx, ny));
						arr[nx][ny] = 2; // 칸 청소
						count++;
						break;
					}
				}
			}
			
			// 주변 4칸 중 청소할 수 있는 칸이 없다면, 방향은 유지한 채로 한 칸 후진
			if(!state) {
				int nx = current.x + dx[(dir+2) % 4];
				int ny = current.y + dy[(dir+2) % 4];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					// 벽이 있어 후진할 수 없으면 작동을 멈춘다
					if(arr[nx][ny] == 1) {
						break;
					}
					else {
						queue.add(new Node(nx, ny));
					}
				}
			}
			
		}
		
	}
}
