import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T; // 테스트케이스의 수
	static int N; // 체스판의 크기
	static int[][] arr; // NxN 체스판
	static Node start; // 시작점
	static Node end; // 도착점
	static boolean[][] visited; // 방문 배열
	static int count = 0; // 이동 횟수
	
	static int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] dy = { 2, 1, -1, -2, -2, -1, 1, 2 };
	
	static class Node {
		int x;
		int y;
		int count;
		
		Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N][N];
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			start = new Node(x, y, count);
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			end = new Node(x, y, count);
			
			bfs(start, end, count);
		}
	}
	
	static void bfs(Node start, Node end, int count) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start.x][start.y] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			if(current.x == end.x && current.y == end.y) {
				System.out.println(current.count);
				return;
			}
			
			for(int i=0; i<8; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
					queue.add(new Node(nx, ny, current.count + 1));
					visited[nx][ny] = true;
				}
			}
		}
		
		
	}

}