import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_17141_연구소2>
 * 1. 입력값 받으면서 바이러스를 놓을 수 있는 위치(2) 저장 -> List
 * 2. 그 중 M개를 선정해 바이러스 살포 -> 조합
 * 		2-1. 큐에 담아 하나씩 꺼냄
 * 		2-2. 상하좌우 확인. 벽이 아니고(즉, 1이 아니고) 방문하지 않았다면 복제 가능 -> 복제 가능한 칸 수 - 1
 * 		2-3. 큐가 빌 때까지 반복
 * 		2-4. 복제 가능한 칸 수가 0이 되어야 모든 칸에 바이러스를 퍼뜨린 것. 0이면 최소 시간 갱신.
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int N;
	static int M;
	static int[][] arr;
	static List<Node> list; // 바이러스를 놓을 수 있는 위치 저장
	static Node[] selected; // 바이러스를 놓을 위치
	static int left; // 벽을 제외한 모든 칸에 바이러스를 퍼뜨리기 위해 남은 칸
	static int answer = Integer.MAX_VALUE;
	
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
		
		arr = new int[N][N];
		list = new ArrayList<>();
		selected = new Node[M];
		
		int wallCnt = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(arr[i][j] == 2) {
					list.add(new Node(i, j));
				} else if(arr[i][j] == 1) {
					wallCnt++;
				}
			}
		}
		
		left = N*N - wallCnt;
		
		comb(0, 0); // 바이러스를 놓을 위치 M개 선정
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	static void comb(int cnt, int start) {
		if(cnt == M) {
			bfs();
			return;
		}
		
		for(int i=start; i<list.size(); i++) {
			selected[cnt] = list.get(i);
			comb(cnt+1, i+1);
		}
	}
	
	static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		int time = 0;
		int cnt = left;
		
		for(int i=0; i<M; i++) {
			queue.add(selected[i]);
			visited[selected[i].x][selected[i].y] = true;
			cnt--;
		}
		
		while(!queue.isEmpty()) {
			// 모든 칸에 바이러스를 퍼뜨렸으면 time 증가시키고 큐를 다 비우기 전에 while문 빠져나와도 됨
			if(cnt == 0) break;
			
			time++;
			
			int size = queue.size();
			
			for(int i=0; i<size; i++) {
				Node current = queue.poll();
				
				for(int dir=0; dir<4; dir++) {
					int nx = current.x + dx[dir];
					int ny = current.y + dy[dir];
					
					if(isRange(nx, ny)) {
						if(arr[nx][ny] != 1 && !visited[nx][ny]) { // 벽이 아니고 방문하지 않은 곳만 바이러스 복제 가능
							queue.add(new Node(nx, ny));
							visited[nx][ny] = true;
							cnt--;
						}
					}
				}
			}
		}
		
		// cnt가 0이 되어야 모든 칸에 바이러스를 퍼뜨린 것
		if(cnt == 0) {
			answer = Math.min(answer, time);
		}
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}

}
