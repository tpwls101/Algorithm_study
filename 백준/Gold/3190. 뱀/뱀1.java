import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_3190_뱀> - 처음 풀었을 때!
 * 
 *  방향	 | →  ↓  ←  ↑  
 * --------------------
 * index | 0  1  2  3
 * --------------------
 *   dx  | 0  1  0 -1 
 *   dy  | 1  0 -1  0
 * 
 * 사과 : 1 / 뱀 : 2 / 빈 칸 : 0
 * 
 * 큐 2개 사용!!
 * 1. 뱀의 좌표 정보 -> |꼬리|...|...|머리|
 * 2. 뱀의 방향 변환 정보 (몇 초 후 어느 방향으로)
 * 
 * => 큐에는 뱀의 좌표 정보가 뱀의 길이만큼 담기고 board에도 현재 위치를 계속해서 보여준다!!!
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int N; // 보드의 크기
	static int K; // 사과의 개수
	static int L; // 뱀의 방향 변환 횟수
	static int[][] board; // NxN 크기의 보드
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	// 뱀의 좌표 정보
	static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	//뱀의 방향 변환 정보
	static class Spin {
		int time; // 게임 시작 x초 후
		String dir; // 방향(왼쪽 or 오른쪽)
		
		Spin(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/* 입력 시작 */
		
		N = Integer.parseInt(br.readLine()); // 보드의 크기
		board = new int[N][N]; // NxN 크기의 보드
		
		K = Integer.parseInt(br.readLine()); // 사과의 개수
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 사과의 위치(행)
			int c = Integer.parseInt(st.nextToken()) - 1; // 사과의 위치(열)
			board[r][c] = 1; // board에서 사과는 1
		}
		
		L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
		Queue<Spin> spin = new ArrayDeque<>(); // 뱀의 방향 변환 정보를 저장할 큐 (몇 초 후에 어느 방향으로 회전할 것인지)
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken()); // 게임 시작하고 몇 초 후인지
			String dir = st.nextToken(); // 회전 방향(L:왼쪽 or D:오른쪽)
			spin.offer(new Spin(time, dir));
		}
		
		/* 입력 끝 */
		
		
		// 초기값 세팅
		int r = 0; // 뱀의 시작 위치(행)
		int c = 0; // 뱀의 시작 위치(열)
		int time = 0; // 몇 초 후인지
		int dir = 0; // 방향 인덱스
		
		Queue<Node> queue = new ArrayDeque<>(); // 뱀의 좌표 정보를 담고 있는 큐   |꼬리|...|...|머리|
		queue.offer(new Node(r, c)); // 큐에 (0,0)좌표 삽입
		board[r][c] = 2;
		
		// 무한으로 돌면서 종료조건(보드를 벗어나거나 자기 자신을 만나는 경우)을 만나면 종료되게 구현
		while(true) {
			
			int next_r = r + dx[dir]; // 다음 좌표(행)
			int next_c = c + dy[dir]; // 다음 좌표(열)
			
			time++; // 1초 증가
			
			// 종료조건1. 뱀이 보드 범위를 벗어나면 종료
			if(next_r < 0 || next_r >= N || next_c < 0 || next_c >= N) {
				break;
			}
			
			// 종료조건2. 뱀이 자기 자신을 만나면 종료
			if(board[next_r][next_c] == 2) {
				break;
			}
			
			// 다음 칸이 빈 칸일 때 -> 뱀의 몸 길이는 그대로! 위치만 한 칸 다음으로 옮겨주기!
			if(board[next_r][next_c] == 0) {
				Node node = queue.poll(); // 큐에 뱀의 꼬리 정보 삭제
				board[node.x][node.y] = 0;
			}
			
			// 뱀의 방향 변환 처리
			if(!spin.isEmpty()) {
				//Spin spinInfo = spin.poll();
				
				// !!! 시간이 같아지면 spin 큐에서 꺼내기 !!!!
				if(time == spin.peek().time) {
					Spin spinInfo = spin.poll();
					
					if(spinInfo.dir.equals("L")) { // 왼쪽
						dir = (4 + (--dir)) % 4;
					} else { // 오른쪽
						dir = ++dir % 4;
					}
				}
			}
			
			// 뱀이 사과를 만나게 되었을 때 + 다음 칸이 빈 칸일 때의 추가 처리
			queue.offer(new Node(next_r, next_c)); // 큐에 뱀의 머리 정보 삽입
			board[next_r][next_c] = 2;
			r = next_r; // 다음 while문에서 다음 좌표를 찾기 위한 처리 (next_r이 다음 while문에서의 현재 좌표)
			c = next_c; // 다음 while문에서 다음 좌표를 찾기 위한 처리 (next_c가 다음 while문에서의 현재 좌표)
		}
		
		System.out.println(time);
	}
	
}
