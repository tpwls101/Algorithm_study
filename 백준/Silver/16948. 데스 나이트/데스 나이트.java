import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] arr;
	
	static int[] dx = { -2, -2, 0, 0, 2, 2 };
	static int[] dy = { -1, 1, -2, 2, -1, 1 };
	
	static class Node {
		int x;
		int y;
		int cnt; // 이동 횟수
		
		Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		
		int answer = bfs(new Node(x1, y1, 0), new Node(x2, y2, 0));
		System.out.println(answer);
	}
	
	static int bfs(Node start, Node destination) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		queue.add(start);
		visited[start.x][start.y] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			if(current.x == destination.x && current.y == destination.y) {
				return current.cnt;
			}
			
			for(int i=0; i<6; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(isRange(nx, ny) && !visited[nx][ny]) {
					queue.add(new Node(nx, ny, current.cnt+1));
					visited[nx][ny] = true;
				}
			}
		}
		
		return -1; // (x2,y2)로 이동 못하면 -1 출력
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}

}
