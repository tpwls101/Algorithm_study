import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * <BJ_2667_단지번호붙이기>
 * 단지의 개수 카운트
 * 단지에 속하는 집의 수 카운트
 * 
 * !!! 놓친 부분 !!!
 * 1. 단지에서 집의 개수를 카운트 할 때마다 max값을 초기화 시켜줘야 함
 * 2. 최대값 갱신하는 max의 위치
 * 3. dfs 호출할 때 max+1 전달 (homeCnt+1 -> X)
 * 
 * 메모리 : 14492kb, 실행시간 : 144ms
 * 
 * @author 유세진
 *
 */

public class Main {
	
	static int N; // 지도의 크기 (5~25)
	static int[][] map; // NxN 지도
	static boolean[][] visited; // 방문 체크 배열
	static int max; // 단지에 속하는 집의 수를 최대로 갱신하기 위해 사용
	static int unitCnt = 0; // 단지의 개수
	
	// 우하좌상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 지도의 크기 (5~25)
		
		map = new int[N][N]; // NxN 지도
		visited = new boolean[N][N]; // 방문 체크 배열
		
		// 지도 입력받기
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split("");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		// 단지에 속하는 집의 수를 저장할 ArrayList
		ArrayList<Integer> homeCnts = new ArrayList<>();
		
		// 지도 탐색
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 아직 방문하지 않았고 집이 있다면
				if(!visited[i][j] && map[i][j] == 1) {
					max = Integer.MIN_VALUE; // 단지에서 집의 개수를 카운트 할 때마다 max값을 초기화 시켜줘야 함
					dfs(i, j, 1); // 현재 x,y좌표와 단지 내에 속하는 집의 수 카운트
					unitCnt++; // 단지 수 증가
					homeCnts.add(max); // 단지에 속하는 집의 수 저장
				}
			}
		}
		
		Collections.sort(homeCnts); // 단지에 속하는 집의 수 오름차순 정렬
		
		System.out.println(unitCnt); // 단지의 개수 출력
		for(int cnt : homeCnts) {
			System.out.println(cnt); // 단지에 속하는 집의 수 출력
		}
		
	}
	
	// x : 현재 x좌표
	// y : 현재 y좌표
	// homeCnt : 현재 단지 내에 속하는 집의 수 카운트
	private static void dfs(int x, int y, int homeCnt) {
		visited[x][y] = true; // 현재 좌표 방문 처리
		//System.out.println("homeCnt : " + homeCnt);
		
		// 현재 좌표에서 4방 탐색
		for(int i=0; i<4; i++) {
			int temp_x = 0;
			int temp_y = 0;
			
			temp_x = x + dx[i];
			temp_y = y + dy[i];
			
			// 배열의 범위를 벗어나지 않고
			if(temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < N) {
				max = Math.max(max, homeCnt); // 단지 내 집의 최대 개수 갱신
				// 아직 방문하지 않았고 집이 있다면 dfs 호출
				if(!visited[temp_x][temp_y] && map[temp_x][temp_y] == 1) {
					dfs(temp_x, temp_y, max+1); // 최대값으로 갱신된 max에 +1 해서 전달
				}
			}
		}
	}

}