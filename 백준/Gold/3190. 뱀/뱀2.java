import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_3190_뱀>
 * 아무것도 없으면 0, 뱀은 1, 사과는 2
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int index = 0; // 이동 인덱스 (L : - / R : +)
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
	
	static class Spin {
		int time; // 몇 초 후 회전
		String dir; // 회전 방향
		
		Spin(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine()); // 보드의 크기
		int K = Integer.parseInt(br.readLine()); // 사과의 개수
		
		int[][] board = new int[N][N];
		
		// 보드에 위치한 사과
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r-1][c-1] = 2;
		}
		
		int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
		
		Queue<Spin> spin = new ArrayDeque<>();
		
		// spin 큐에 회전 정보 저장
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			spin.add(new Spin(time, dir));
		}
		
		Queue<Node> queue = new ArrayDeque<>(); // 뱀의 위치 정보 저장 (뱀의 길이만큼)
		queue.add(new Node(0, 0));
		board[0][0] = 1;
		
		int count = 0; // 게임이 끝나는데 걸리는 시간
		int x = 0; // 현재 뱀의 머리 위치 (x좌표)
		int y = 0; // 현재 뱀의 머리 위치 (y좌표)
		
		// 뱀 이동 시작
		while(true) {
			count++; // 시간 카운트 증가
			
			int nx = x + dx[index];
			int ny = y + dy[index];
			
			// 종료 조건 1 : 뱀이 벽에 부딪히면 게임 종료
			// 종료 조건 2 : 뱀이 자기자신의 몸과 부딪히면 게임 종료
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 1) {
				break;
			}
			
			// 다음 좌표가 아무것도 없는 곳이라면 몸 길이 그대로 유지
			if(board[nx][ny] == 0) {
				Node tailPos = queue.poll(); // 큐에서 뱀의 꼬리 좌표 빼고
				board[tailPos.x][tailPos.y] = 0; // 꼬리 부분 0으로 바꿈
			}
			
			// 사과를 만나던 안만나던 한칸씩 전진하는 뱀의 머리
			queue.add(new Node(nx, ny));
			board[nx][ny] = 1;
			
			// 다음 뱀의 방향 확인
			if(!spin.isEmpty()) {
				// 시간이랑 현재 count랑 같으면 변환 정보 하나 빼기
				if(spin.peek().time == count) {
					Spin spinInfo = spin.poll();
					if(spinInfo.dir.equals("L")) {
						index = (index+3) % 4;
					} else {
						index = (index+1) % 4;
					}
				}
			}
			
			// 다음 뱀의 머리 위치 업데이트
			x = nx;
			y = ny;
		}
		
		System.out.println(count);
	}

}
