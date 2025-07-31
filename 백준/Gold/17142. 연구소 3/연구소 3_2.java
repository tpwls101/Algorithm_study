import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_17142_연구소3>
 * 조합 + BFS문제
 * BJ_17142_연구소2와의 차이점은 연구소2는 2가 바이러스를 놓을 수 있는 이라 빈칸 취급하지만, 연구소3은 2인 칸에 이미 바이러스가 놓여있다. 활성/비활성의 차이. 처음엔 비활성 상태.
 * 
 * 1. 입력값 받으면서 비활성 바이러스의 위치 저장 -> List
 * 2. 위치들 중 M개 골라 바이러스를 활성으로 바꿈 -> 조합
 * 		2-1. 큐에 바이러스 좌표 담아 하나씩 꺼냄. 단, 큐의 사이즈 만큼 for문 돌린게 1초.
 * 		2-2. 상하좌우 탐색
 * 		2-3. 빈칸이고(0이고) 방문하지 않았으면 큐에 추가 -> 빈칸 수 - 1 (0인 곳만 빈칸 수로 카운트)
 * 			  비활성 바이러스가 있는 칸으로도 바이러스를 퍼뜨릴 수 있음에 주의해야 한다! 따라서 2이고 방문하지 않았으면 큐에 추가. 단, 빈칸 수 처리는 하지 않는다.
 * 		2-4. 빈칸 수가 0이 되면 모든 빈칸에 바이러스를 퍼뜨린 것으로 최소 시간 갱신
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M; // 활성으로 만들 바이러스 개수
	static int[][] arr;
	static List<Node> list; // 바이러스의 위치 저장
	static Node[] selected; // 활성화할 선택된 바이러스 저장
	static int emptyCnt = 0; // 빈칸 수
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
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(arr[i][j] == 2) {
					list.add(new Node(i, j));
				} else if(arr[i][j] == 0) {
					emptyCnt++;
				}
			}
		}
		
		comb(0, 0); // 바이러스 중 활성화할 바이러스 M개 고르기
		
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
		int cnt = emptyCnt;
		
		for(int i=0; i<M; i++) {
			queue.add(selected[i]);
			visited[selected[i].x][selected[i].y] = true;
		}
		
		while(!queue.isEmpty()) {
			// 모든 빈칸에 바이러스를 퍼뜨렸으면 큐 비우기 전에 while문 빠져나오기. 안그러면 time++ 수행됨.
			if(cnt == 0) break;
			
			int size = queue.size();
			
			for(int i=0; i<size; i++) {
				Node current = queue.poll();
				
				for(int dir=0; dir<4; dir++) {
					int nx = current.x + dx[dir];
					int ny = current.y + dy[dir];
					
					if(isRange(nx, ny)) {
						if(arr[nx][ny] == 0 && !visited[nx][ny]) { // 빈칸이고 방문하지 않은 칸으로 바이러스 복제할 때는 빈칸 수 -1 처리
							queue.add(new Node(nx, ny));
							visited[nx][ny] = true;
							cnt--;
						} else if(arr[nx][ny] == 2 && !visited[nx][ny]) { // 비활성 바이러스가 있는 칸으로도 바이러스 복제는 가능!! 다만 빈칸 수는 처리하면 안됨.
							queue.add(new Node(nx, ny));
							visited[nx][ny] = true;
						}
					}
				}
			}
			time++;
		}
		
		// 모든 빈칸에 바이러스를 퍼뜨린 경우에만 최소 시간 갱신
		if(cnt == 0) {
			answer = Math.min(answer, time);
		}
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >=0 && y < N) {
			return true;
		}
		return false;
	}

}
