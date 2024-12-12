import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 공간의 크기
	static int[][] arr; // 공간의 상태
	static boolean[][] visited;
	static Shark shark;
	static int eatCnt = 0; // 현재까지 상어가 먹은 물고기의 수
	static List<Node> candidate; // 상어가 먹을 수 있는 물고기 후보 리스트
	static int answer = 0; // 아기 상어가 엄마 상어에게 도움을 청하지 않고 물고기를 잡아먹을 수 있는 시간
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	static class Shark {
		int x;
		int y;
		int size;
		
		Shark(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
	
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int cnt;
		
		Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.cnt == o.cnt) {
				if(this.x == o.x) {
					return this.y - o.y; // 3. 열 기준 오름차순
				}
				return this.x - o.x; // 2. 행 기준 오름차순
			}
			return this.cnt - o.cnt; // 1. 거리 기준 오름차순
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
        		if(arr[i][j] == 9) {
        			shark = new Shark(i, j, 2); // 가장 처음 아기 상어의 크기는 2
        		}
        	}
        }
        
        while(true) {
        	// 먹을 수 있는 물고기 후보를 리스트에 저장
        	bfs();
        	
        	// 더 이상 먹을 수 있는 물고기가 없으면 엄마 상어에게 도움을 요청
        	if(candidate.size() == 0) break;
        	
        	Collections.sort(candidate);
        	Node best = candidate.get(0);
        	
        	arr[shark.x][shark.y] = 0; // 원래 상어가 있던 칸은 빈 칸이 됨
        	eatCnt++; // 상어가 먹은 물고기 수 증가
        	if(eatCnt == shark.size) {
        		shark.size++; // 상어의 크기와 같은 수의 물고기를 먹으면 크기 1 증가
        		eatCnt = 0;
        	}
        	shark = new Shark(best.x, best.y, shark.size);
        	arr[shark.x][shark.y] = 9; // 새로운 상어의 위치

        	answer += best.cnt; // 해당 물고기를 먹는데 걸리는 시간만큼 더해주기
        }
        
		System.out.println(answer);
	}
	
	static void bfs() {
		candidate = new ArrayList<>();
		visited = new boolean[N][N];
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(shark.x, shark.y, 0));
		visited[shark.x][shark.y] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if(!visited[nx][ny]) {
						// 상어보다 크기가 작거나 같은 물고기가 있는 칸은 모두 지나갈 수 있다
						if(arr[nx][ny] <= shark.size) {
							queue.add(new Node(nx, ny, current.cnt + 1));
							visited[nx][ny] = true;
							
							// 상어보다 크기가 작은 물고기는 먹을 수 있음
							if(arr[nx][ny] != 0 && arr[nx][ny] < shark.size) {
								candidate.add(new Node(nx, ny, current.cnt + 1));
							}
						}
					}
				}
			}
		}
	}

}