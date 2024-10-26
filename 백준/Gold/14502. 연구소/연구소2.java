import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_14502_연구소>
 * 1. 이중 for문 돌면서 빈 칸이라면 일단 벽을 3개 세운다. -> DFS
 * 2. 벽을 3개 세웠으면 바이러스가 있는 곳을 찾아 퍼뜨리고 안전 영역의 크기를 구한다. -> BFS
 * 		이 때, 배열 복사해서 사용해야 한다.
 * 
 * visited 배열이 필요가 없다..!
 * 
 * @author YooSejin
 *
 */

public class BJ_14502_연구소 {
	
	static int N; // 지도의 세로 크기
	static int M; // 지도의 가로 크기
	static int[][] arr; // 지도 (0:빈 칸 / 1:벽 / 2:바이러스)
	static int max = 0; // 안전 영역의 최댓값
	static int[][] copy; // 바이러스를 퍼뜨리기 위해 새로운 배열 하나 생성 (기존 배열을 유지해야 하기 때문)
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
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
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		
		System.out.println(max);
	}
	
	static void dfs(int cnt) {
		if(cnt == 3) {
			// 벽이 세워진 배열 복사
			copy = new int[N][M];
			for(int i=0; i<N; i++) {
				copy[i] = Arrays.copyOf(arr[i], arr[i].length);
//				copy[i] = arr[i].clone();
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j] == 2) { // 원래 바이러스가 있던 곳에서 퍼뜨리기
						bfs(new Node(i, j));
					}
				}
			}
			
			// 안전영역 크기 구하기
			int count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(copy[i][j] == 0) {
						count++;
					}
				}
			}
			max = Math.max(max, count);
			
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0) { // 빈 칸인 곳에만 벽을 세울 수 있음
					arr[i][j] = 1;
					dfs(cnt+1);
					arr[i][j] = 0;
				}
			}
		}
	}
	
	static void bfs(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if(copy[nx][ny] == 0) {
						copy[nx][ny] = 2;
						queue.add(new Node(nx, ny));
					}
				}
			}
		}
	}

}
