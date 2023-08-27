import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1987_알파벳>
 * 배열의 x,y좌표와 count를 매개변수로 전달하여 dfs 호출
 * 이 때, 처음 칸도 count에 해당하므로 1 전달
 * alphabets[] 배열에 내가 이동 중에 사용한 현재 알파벳을  true로 변경
 * 그리고 4방을 돌면서 배열의 범위를 벗어나지 않으면 먼저 max값 갱신
 * 그리고 사용한 알파벳이 아니라면 다음 재귀 호출
 * 현재 알파벳에서 4방 탐색 후 사용할 수 없으면 다시 방문 처리 취소
 * 
 * @author 유세진
 *
 */

public class Main {
	
	static int R; // 세로 R칸
	static int C; // 가로 C칸
	static char[][] input; // 입력받은 알파벳을 저장할 배열
	static boolean[] alphabets = new boolean[26]; // 알파벳을 사용했는지 확인할 배열
	static int max = Integer.MIN_VALUE; // 말이 지날 수 있는 최대의 칸 수를 갱신하기 위해 사용
	
	// 우하좌상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		input = new char[R][C]; // 알파벳을 입력받아 저장할 배열
		
		// 알파벳 입력받기
		for(int i=0; i<R; i++) {
			char[] cArr = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				input[i][j] = cArr[j];
			}
		}
		
		dfs(0, 0, 1); // 시작 좌표에서 이미 카운트 1
		
		System.out.println(max);
	}
	
	// x : 현재 x좌표
	// y : 현재 y좌표
	// count : 말이 지난 칸 수
	private static void dfs(int x, int y, int count) {
		char alpha = input[x][y]; // 현재 좌표의 알파벳 확인
		alphabets[alpha - 65] = true; // 현재 알파벳 사용중임
		
		for(int i=0; i<4; i++) {
			int temp_x = x + dx[i];
			int temp_y = y + dy[i];
			
			// 배열의 범위를 벗어나지 않으면
			if(temp_x >= 0 && temp_x < R && temp_y >= 0 && temp_y < C) {
				max = Math.max(max, count); // 말이 지날 수 있는 최대 칸 수 갱신
				// 사용한 알파벳이 아니면 탐색
				if(!alphabets[input[temp_x][temp_y] - 65]) {
					dfs(temp_x, temp_y, count+1);
				}
			}
		}
		
		alphabets[alpha - 65] = false; // 현재 알파벳에서 4방 탐색 후 사용할 수 없으면 다시 방문 처리 취소
	}

}