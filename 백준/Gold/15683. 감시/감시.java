import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_15683_감시>
 * 0 : 빈 칸 / 1~5 : CCTV / 6 : 벽
 * 
 * 주의사항
 * 한가지 경우가 만들어지면 사각지대의 최소 크기 구하고 copy 배열을 초기화하면 안됨
 * 그러면 마지막 cctv의 다음 방면을 탐색하는데 이전의 cctv가 감시한 것이 초기화되기 때문
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static int[][] arr;
	static List<CCTV> list;
	static int answer = Integer.MAX_VALUE; // 사각지대의 최소 크기
	
	// 우하좌상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	static int[][][] mode = { { {0} },
							  { {0}, {1}, {2}, {3} }, // 1번 cctv
							  { {0,2}, {1,3} }, // 2번 cctv
							  { {0,3}, {0,1}, {1,2}, {2,3} }, // 3번 cctv
							  { {0,2,3}, {0,1,3}, {0,1,2}, {1,2,3} }, // 4번 cctv
							  { {0,1,2,3} } // 5번 cctv
							};
	
	static class CCTV {
		int x;
		int y;
		int num;
		
		CCTV(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				// 리스트에 cctv 저장
				if(arr[i][j] != 0 && arr[i][j] != 6) {
					list.add(new CCTV(i, j, arr[i][j]));
				}
			}
		}
		
		dfs(0, arr);
		
		System.out.println(answer);
	}
	
	static void dfs(int cnt, int[][] arr) {
		if(cnt == list.size()) {
//			System.out.println("cctv 감시 구역 확인");
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			// 사각지대 크기 구하기
			int count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j] == 0) {
						count++;
					}
				}
			}
//			System.out.println("count = " + count);
			answer = Math.min(answer, count);
			
//			System.out.println("초기화 됐는지 확인");
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			return;
		}
		
		CCTV cctv = list.get(cnt); // 현재 cctv
		int cctvNum = list.get(cnt).num; // 현재 cctv 번호
		
		for(int i=0; i<mode[cctvNum].length; i++) { // x번 cctv가 탐색할 수 있는 방면의 경우의 수
			// 배열 복사
			int[][] copy = new int[N][M];
			for(int j=0; j<N; j++) {
				copy[j] = arr[j].clone();
			}
			
			// 카메라 한대가 감시할 수 있는 모든 방면 탐색
			for(int j=0; j<mode[cctvNum][i].length; j++) {
				int dir = mode[cctvNum][i][j];
				watch(cctv, dir, copy); // dir 방향(상하좌우 중 하나)으로 cctv 감시
			}
			
			dfs(cnt+1, copy); // 다음 카메라 탐색
		}
	}
	
	static void watch(CCTV cctv, int dir, int[][] copy) {
		Queue<CCTV> queue = new ArrayDeque<>();
		queue.add(cctv);
		
		while(!queue.isEmpty()) {
			CCTV current = queue.poll();
			
			int nx = current.x + dx[dir];
			int ny = current.y + dy[dir];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if(arr[nx][ny] != 6) {
					queue.add(new CCTV(nx, ny, cctv.num));
					if(arr[nx][ny] == 0) {
						copy[nx][ny] = -1;
					}
				}
			}
		}
//		
//		System.out.println("확인");
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

}