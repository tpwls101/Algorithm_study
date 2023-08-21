import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_1697_숨바꼭질>
 * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간은 몇 초 후?
 * 
 * 걸으면 -> X-1 or X+1
 * 순간이동 -> 2*X
 * 
 * @author SSAFY
 *
 */

public class Main {
	
	static int N; // 수빈이의 위치
	static int K; // 동생의 위치
	static boolean[] visited = new boolean[100001]; // 방문 체크 배열 (0 <= N <= 100000 이므로 배열의 크기는 100001)
	
	static class Pos {
		int x; // 위치
		int second; // 걸리는 시간
		
		public Pos(int x, int second) {
			this.x = x;
			this.second = second;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
		K = Integer.parseInt(st.nextToken()); // 동생의 위치
		
		bfs(new Pos(N, 0));
		
	}
	
	private static void bfs(Pos pos) {
		
		Queue<Pos> queue = new ArrayDeque<>(); // 큐 생성
		queue.offer(pos);
		visited[pos.x] = true;
		
		while(!queue.isEmpty()) {
			Pos current = queue.poll(); // 현재 위치와 걸리는 시간
			
			if(current.x == K) { // 동생을 만나면
				System.out.println(current.second); // 걸린 시간 출력
				break;
			}
			
			// 점의 위치는 0보다 크거나 같고 100,000보다 작거나 같아야 함
			// -1 걷는데 범위를 벗어나지 않고 방문하지 않았다면
			if(current.x - 1 >= 0 && !visited[current.x - 1]) { // -1 (걷기)
				queue.offer(new Pos(current.x - 1, current.second+1));
				visited[current.x - 1] = true;
			}
			
			// +1 걷는데 범위를 벗어나지 않고 방문하지 않았다면
			if(current.x + 1 <= 100000 && !visited[current.x + 1]) { // +1 (걷기)
				queue.offer(new Pos(current.x + 1, current.second+1));
				visited[current.x + 1] = true;
			}
			
			// *2 순간이동하는데 범위를 벗어나지 않고 방문하지 않았다면
			if(current.x * 2 <= 100000 && !visited[current.x * 2]) { // *2 (순간이동)
				queue.offer(new Pos(current.x * 2, current.second+1));
				visited[current.x * 2] = true;
			}
			
		}
		
		
	}

}