import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] arr;
	static int maxHeight = 0;
	static boolean[][] visited;
	static int answer = 1;
	
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
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, arr[i][j]);
			}
		}
		
		// 비가 높이1부터 가장 높은 높이까지 올 수 있다.
		for(int rain=1; rain<maxHeight; rain++) {
			visited = new boolean[N][N]; // 초기화
			int count = bfs(rain); // 비가 높이 rain만큼 올 때 안전영역의 갯수
			answer = Math.max(answer, count);
		}
		
		System.out.println(answer);
	}
	
	static int bfs(int rain) {
		Queue<Node> queue = new ArrayDeque<>();
		int count = 0; // 안전영역의 갯수
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] > rain && !visited[i][j]) {
					queue.add(new Node(i, j));
					visited[i][j] = true;
					count++;
					
					while(!queue.isEmpty()) {
						Node current = queue.poll();
						
						for(int k=0; k<4; k++) {
							int nx = current.x + dx[k];
							int ny = current.y + dy[k];
							
							if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
								if(arr[nx][ny] > rain && !visited[nx][ny]) {
									queue.add(new Node(nx, ny));
									visited[nx][ny] = true;
								}
							}
						}
					}
				}
			}
		}
		
//		System.out.println("비가 높이 " + rain + "만큼 왔을 때 안전영역의 갯수 = " + count);
		return count;
	}
	

}
