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
 * 주의사항!!
 * 2(비활성화된 바이러스)를 만나도 지나갈 수는 있다.
 * 단, 2를 만났을 때 시간을 카운트하여 갱신하면 안된다.
 * 예를 들어, 다른 곳에서는 탐색이 끝났는데, 한 곳에서 바이러스를 만나 시간을 +1하게 되면 이를 걸리는 시간의 최댓값으로 갱신하지 않도록 주의해야 한다.
 * 
 * 방문 개수로 이를 처리하기가 어려워
 * 빈 칸을 처리한 개수로 카운팅하는 게 더 낫다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 연구소의 크기
	static int M; // 놓을 수 있는 바이러스의 개수	
	static int[][] arr;
	static int emptyCnt = 0; // 빈 칸의 개수
	static List<Node> virus = new ArrayList<>(); // 바이러스의 위치를 저장
	static Node[] selected; // 활성화하기 위해 선택된 바이러스
	static int answer = Integer.MAX_VALUE; // 모든 칸에 바이러스를 퍼뜨리는데 걸리는 최소 시간
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	static class Node {
		int x;
		int y;
		int time; // 해당 칸까지 바이러스가 퍼지는데 걸리는 시간
		
		Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][N];
        selected = new Node[M];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		
        		if(arr[i][j] == 0) emptyCnt++;
        		if(arr[i][j] == 2) virus.add(new Node(i, j, 0));
        	}
        }
        
        // 바이러스를 퍼뜨릴 빈 칸이 없으면 바로 0 출력
        if(emptyCnt == 0) {
        	System.out.println(0);
        } else {
            comb(0, 0); // 바이러스 놓을 위치의 조합 구하기
            
            // 바이러스를 어떻게 놓아도 모든 칸에 퍼뜨릴 수 없으면 -1 출력
            if(answer == Integer.MAX_VALUE) {
            	System.out.println(-1);
            } else {
            	System.out.println(answer);
            }
        }
	}
	
	static void comb(int start, int cnt) {
		if(cnt == M) {
			bfs(); // 바이러스 퍼뜨리기
			return;
		}
		
		for(int i=start; i<virus.size(); i++) {
			selected[cnt] = virus.get(i);
			comb(i+1, cnt+1);
		}
	}
	
	static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		int empty = emptyCnt;
		
		for(int i=0; i<selected.length; i++) {
			queue.add(selected[i]);
			visited[selected[i].x][selected[i].y] = true;
		}
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if(arr[nx][ny] == 0 && !visited[nx][ny]) {
						queue.add(new Node(nx, ny, current.time+1));
						visited[nx][ny] = true;
						empty--;
					} else if(arr[nx][ny] == 2 && !visited[nx][ny]) {
						queue.add(new Node(nx, ny, current.time+1));
						visited[nx][ny] = true;
					}
				}
				
				// 모든 빈 칸에 바이러스를 퍼뜨리면 시간 갱신
				if(empty == 0) {
					answer = Math.min(answer, current.time + 1);
					return;
				}
			}
		}
	}

}
