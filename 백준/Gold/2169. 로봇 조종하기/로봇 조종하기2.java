import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_2169_로봇조종하기>
 * dfs/bfs로 풀기에는 범위가 1000까지이므로 시간초과가 날 수 있다.
 * 그리고 방문 체크를 한 부분을 돌아서 올 수 있는데 방문을 못해서 정확한 계산이 안된다.
 * 따라서 DP로 풀어야 한다.
 * 
 * 주의할 점
 * 1. 첫 번째 줄은 올 수 있는 방향이 왼쪽에서밖에 못온다.
 * 2. 두 번째 줄부터는 왼쪽, 오른쪽, 위에서 올 수 있는데
 * 		이를 한 번에 처리하면 왼쪽 방향으로 차례대로 계산한다고 할 때
 * 		오른쪽 dp값은 무조건 0이기에 최댓값 갱신이 제대로 되지 않는다.
 * 		따라서 왼쪽 방향으로 최댓값을 갱신해주고, 오른쪽 방향으로도 최댓값을 갱신해야 최종 최댓값이 된다.
 * 		- 왼쪽 계산 : 왼쪽 또는 위에서 오는 것 중 큰 값을 저장
 * 		- 오른쪽 계산 : 오른쪽 또는 위에서 오는 것 중 큰 값을 저장
 * 		그리고 왼쪽 또는 오른쪽 중 큰 값을 i번째 줄 dp에 저장
 * 
 * @author YooSejin
 *
 */

public class BJ_2169_로봇조종하기 {
	
	static int N;
	static int M;
	static int[][] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		dp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 첫 번째 줄은 왼쪽에서만 올 수 있다. 즉, 오른쪽으로만 갈 수 있다.
		dp[0][0] = arr[0][0];
		for(int i=1; i<M; i++) {
			dp[0][i] = dp[0][i-1] + arr[0][i];
		}
		
		// 두 번째 줄부터 마지막 줄까지는 왼쪽, 오른쪽, 위에서 올 수 있다.
		for(int i=1; i<N; i++) {
			int[] left = new int[M];
			int[] right = new int[M];
			
			// 왼쪽으로 이동할 때
			left[0] = dp[i-1][0] + arr[i][0];
			for(int j=1; j<M; j++) {
				left[j] = Math.max(dp[i-1][j], left[j-1]) + arr[i][j];
			}
			
			// 오른쪽으로 이동할 때
			right[M-1] = dp[i-1][M-1] + arr[i][M-1];
			for(int j=M-2; j>=0; j--) {
				right[j] = Math.max(dp[i-1][j], right[j+1]) + arr[i][j];
			}
			
			// 왼쪽에서 올 때와 오른쪽에서 올 때 중 더 큰 값으로 dp에 저장
			for(int j=0; j<M; j++) {
				dp[i][j] = Math.max(left[j], right[j]);
			}
		}
		
		System.out.println(dp[N-1][M-1]);
	}

}
