import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_3055_탈출>
 * . : 빈칸 
 * * : 물
 * X : 돌
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	public static int r;
	public static int c;
	public static char[][] map;
	public static int answer = Integer.MAX_VALUE; // 고슴도치가 비버 굴로 이동하기 위해 필요한 최소시간
	
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	
	public static class Node {
		int x;
		int y;
		int time; // 현재까지 걸린 시간
		
		Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken()); // 행
		c = Integer.parseInt(st.nextToken()); // 열
		
		map = new char[r][c];
		
		// 입력
		for(int i=0; i<r; i++) {
			String s = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		bfs();
		
		System.out.println(answer == Integer.MAX_VALUE ? "KAKTUS" : answer);
	}
	
	public static void bfs() {
		Queue<Node> hedgehog = new ArrayDeque<>();
		Queue<Node> water = new ArrayDeque<>();
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j] == 'S') { // 고슴도치를 만나면
					hedgehog.add(new Node(i, j, 0)); // 고슴도치 큐에 삽입
				}
				if(map[i][j] == '*') { // 물을 만나면
					water.add(new Node(i, j, 0)); // 물 큐에 삽입
				}
			}
		}
		
		while(!hedgehog.isEmpty()) { // 고슴도치가 이동하기 때문에 고슴도치 큐가 비어있지 않다면 실행
			
			// 물 먼저 이동시킨 후 고슴도치 이동 (고슴도치가 물이 찰 예정인 곳으로는 이동 불가이기 때문)
			
			// 물 (홍수 발생)
			// 맵에 물이 여러 개 있으면 1초 동안 각 위치에서 물을 퍼뜨려줘야 한다!
			int len = water.size();
			for(int a=0; a<len; a++) {
				Node nWater = water.poll();		
				for(int i=0; i<4; i++) {
					int temp_wx = nWater.x + dx[i];
					int temp_wy = nWater.y + dy[i];
					
					// 범위를 	벗어나지 않고
					if(temp_wx >= 0 && temp_wx < r && temp_wy >= 0 && temp_wy < c) {
						if(map[temp_wx][temp_wy] == '.') { // 빈 칸일 때에만
							map[temp_wx][temp_wy] = '*'; // 물로 변함
							water.add(new Node(temp_wx, temp_wy, nWater.time+1));
						}
					}
				}
			}
			
			// 고슴도치 이동
			// 1초 동안 ~~
			len = hedgehog.size();
			for(int a=0; a<len; a++) {
				Node nHedge = hedgehog.poll();
				for(int i=0; i<4; i++) {
					int temp_hx = nHedge.x + dx[i];
					int temp_hy = nHedge.y + dy[i];
					
					// 범위를 	벗어나지 않고
					if(temp_hx >= 0 && temp_hx < r && temp_hy >= 0 && temp_hy < c) {
						if(map[temp_hx][temp_hy] == 'D') { // 비버굴에 도착하면
							answer = Math.min(answer, nHedge.time+1);
							//answer = nHedge.time+1;
						}
						if(map[temp_hx][temp_hy] == '.') { // 빈 칸일 때에만
							map[temp_hx][temp_hy] = 'S'; // 고슴도치 이동
							//map[nHedge.x][nHedge.y] = '.'; // 원래 고슴도치가 있던 곳은 빈 칸 처리x -> 다시 되돌아가면 안되니까!
							hedgehog.add(new Node(temp_hx, temp_hy, nHedge.time+1));
						}
					}
				}
			}
			
		}
		
	}

}