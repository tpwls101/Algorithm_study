import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_11048_이동하기>
 * 메모이제이션을 활용하여 (r,c)까지 오는데 주운 사탕의 최대 개수를 저장한다.
 * (r,c)에는 좌, 좌상, 상에서 값이 들어올 수 있으므로 세 곳의 값을 비교하여 최댓값을 구하고
 * 그 최댓값에 (r,c)의 값을 더해주면 (r,c)까지 오는데 주운 사탕의 최대 개수가 된다.
 * 
 * 메모리 : 66984kb, 실행시간 : 560ms
 * 
 * @author 유세진
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // NxM 미로
		int M = Integer.parseInt(st.nextToken()); // NxM 미로
		
		int[][] maze = new int[N][M]; // NxM 미로
		
		// 미로에 놓여진 사탕 개수 입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][M+1]; // 메모이제이션 ((r,c)까지 오는데 주운 사탕의 최대 개수 저장)
		
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=M; c++) {
				// (r,c)에 올 수 있는 좌, 좌상, 상의 값 중 최대값을 구해 현재 (r,c)의 사탕 개수를 더해 저장
				dp[r][c] = Math.max(dp[r][c-1], Math.max(dp[r-1][c-1], dp[r-1][c])) + maze[r-1][c-1];
			}
		}
		
		System.out.println(dp[N][M]);
		
	}

}
