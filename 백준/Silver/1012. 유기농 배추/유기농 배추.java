import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_1012_유기농배추>
 * bfs 문제
 * 배열을 처음부터 탐색하면서 상하좌우 탐색
 * 큐에 유기농배추가 있는 위치를 나타내는 노드를 담고 큐가 빌 때까지 탐색
 * 큐가 비면 더 이상 인접한 배추가 없으므로 필요한 배추흰지렁이의 개수 +1
 * 
 * 주의 : count static 변수로 선언 -> 테스트케이스마다 초기화 시켜 줄 것!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int T; // 테스트케이스 수
	static int M; // 배추밭의 가로길이
	static int N; // 배추밭의 세로길이
	static int K; // 배추가 심어져있는 위치의 개수
	static int X; // 배추의 x좌표
	static int Y; // 배추의 y좌표
	static int[][] arr;
	static boolean[][] visited;
	static int count; // 배추흰지렁이 개수
	
	static int[] dx = { 0, 1, 0, -1 }; // 행
	static int[] dy = { 1, 0, -1, 0 }; // 열
	
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
		T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				arr[Y][X] = 1;
			}
			
			// 배열 확인
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					// 유기농배추가 있고 아직 방문하지 않았다면 주변 탐색
					if(arr[i][j] == 1 && !visited[i][j]) {
						bfs(new Node(i, j));
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	public static void bfs(Node node) {		
		// 현재 좌표 : 큐에 추가 + 방문처리
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(node.x, node.y));
		visited[node.x][node.y] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll(); // 현재 좌표
			for(int i=0; i<4; i++) {
				// 다음 좌표
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				// 배추밭의 범위를 벗어나지 않아야 함
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					// 다음 좌표에 배추가 있고 방문하지 않았다면 탐색
					if(arr[nx][ny] == 1 && !visited[nx][ny]) {
						queue.add(new Node(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		count++;
	}

}