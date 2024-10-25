import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * <BJ_1941_소문난칠공주>
 * 0~24 인덱스를 사용
 * 조합 만들면서 임도연파 학생 수를 확인하면 조합 만들고 나서 확인하는 것보다 시간을 단축시킬 수 있음
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static String[][] arr; // 5x5 배열
	static boolean[][] visited;
	static Node[] princess; // 조합을 저장할 배열
	static int answer = 0; // 소문난 칠공주를 결성할 수 있는 경우의 수
	
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
		
		arr = new String[5][5];
		visited = new boolean[5][5];
		princess = new Node[7];
		
		for(int i=0; i<5; i++) {
			String[] s = br.readLine().split("");
			arr[i] = Arrays.copyOf(s, s.length);
		}
		
		comb(0, 0, 0);
		
		System.out.println(answer);
	}
	
	static void comb(int start, int cnt, int yCnt) {
		// 임도연파 학생이 3명을 넘으면 조합 실패
		if(yCnt > 3) {
			return;
		}
		
		// 임도연파 학생이 3명을 이하고, 7명 조합이 완성되면 인접해 있는지 확인
		if(cnt == 7) {
			bfs();
			return;
		}
		
		for(int i=start; i<25; i++) {
			int x = i / 5;
			int y = i % 5;
			
			if(visited[x][y]) continue;
			princess[cnt] = new Node(x, y);
			visited[x][y] = true;
			if(arr[x][y].equals("Y")) {
				comb(i+1, cnt+1, yCnt+1);
			} else {
				comb(i+1, cnt+1, yCnt);
			}
			visited[x][y] = false;
		}
	}
	
	static void bfs() {
		boolean[][] tempVisited = new boolean[5][5];
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(princess[0]);
		tempVisited[princess[0].x][princess[0].y] = true;
		
		int cnt = 1; // 인접한 학생 수
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
					// 조합 중 하나이고 인접확인 할 때는 아직 확인안한 학생이면 큐에 추가
					if(visited[nx][ny] && !tempVisited[nx][ny]) {
						queue.offer(new Node(nx, ny));
						tempVisited[nx][ny] = true;
						cnt++;
					}
				}
			}
		}
		
		// 7명 모두 인접해 있으면 경우의 수 증가
		if(cnt == 7) answer++;
	}

}