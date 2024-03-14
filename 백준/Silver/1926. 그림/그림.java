import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_1926_그림>
 * 주의사항 : bfs를 시작함과 동시에 그림의 크기는 1이 되므로 size++ 해주기
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 도화지의 세로 길이(행)
	static int M; // 도화지의 가로 길이(열)
	static int arr[][]; // 도화지 배열
	static boolean visited[][];
	static int count = 0; // 그림의 개수
	static int size; // 그림 하나의 넓이
	static int maxSize = 0; // 가장 넓은 그림의 넓이
	
	static int[] dx = { 0, 1, 0, -1 }; // 행
	static int[] dy = { 1, 0, -1, 0 }; // 열
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
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
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					size = 0;
					bfs(new Node(i, j));
				}
			}
		}
		
		System.out.println(count);
		System.out.println(maxSize);
	}
	
	public static void bfs(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		visited[node.x][node.y] = true;
		size++;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=0; i<4; i++) {
				// 다음 좌표
				int x = current.x + dx[i];
				int y = current.y + dy[i];
				
				if(x >= 0 && x < N && y >= 0 && y < M) {
					if(arr[x][y] == 1 && !visited[x][y]) {
						queue.add(new Node(x, y));
						visited[x][y] = true;
						size++;
					}
				}
			}
		}
		
		count++;
		maxSize = Math.max(size, maxSize);
	}

}