import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1987_알파벳>
 * 배열의 x,y좌표와 count를 매개변수로 전달하여 dfs 호출
 * 이 때, 처음 칸도 count에 해당하므로 1 전달
 * alphabet[] 배열에 내가 이동 중에 알파벳을 사용했으면 true로 변경
 * 그리고 4방을 돌면서 범위를 벗어나면 다음 방향 탐색, 아니면 이미 사용중인 알파벳인지 확인, 아니라면 다음 재귀 호출
 * 방문을 마쳤을 때 다시 돌아와서 방문확인을 false로 변경
 * 매번 count가 최대값인지 확인해서 갱신
 * 
 * @author 유세진
 *
 */

public class Main {
	
	static int R; // 세로 R칸
	static int C; // 가로 C칸
	static char[][] arr; // 알파벳을 저장할 배열
	static boolean[] alphabet; // 이동한 경로를 따라 알파벳이 사용되었는지를 저장할 배열
	static int max = Integer.MIN_VALUE;
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // 세로 R칸
		C = Integer.parseInt(st.nextToken()); // 가로 C칸
		
		arr = new char[R][C]; // 알파벳을 저장할 배열
		alphabet = new boolean[26];
		
		// 알파벳 입력받기
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		dfs(0, 0, 1);
		
		System.out.println(max);
		
	}
	
	private static void dfs(int x, int y, int count) {

		alphabet[arr[x][y] - 65] = true; // 사용한 알파벳 체크
		
		for(int i=0; i<4; i++) {
			int temp_x = x + dx[i];
			int temp_y = y + dy[i];
			
			// 배열의 범위를 벗어나면 다음 탐색
			if(temp_x < 0 || temp_x >= R || temp_y < 0 || temp_y >= C) {
				continue;
			}
				
			// 이미 사용한 알파벳이면 리턴
			if( alphabet[ arr[temp_x][temp_y] - 65 ] ) { // 이미 사용한 알파벳
				continue;
			}
			
			// 사용한 알파벳이 아니라면 다음 탐색 (재귀 호출)
			dfs(temp_x, temp_y, count+1);
			
			alphabet[ arr[temp_x][temp_y] - 65] = false; // 다음에 다시 방문할 수도 있으니 false 처리
			
		}
		
		max = Math.max(max, count);
	}

}