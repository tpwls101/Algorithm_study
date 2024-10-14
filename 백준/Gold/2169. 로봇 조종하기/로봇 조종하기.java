import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_2169_로봇조종하기>
 * bfs랑 배열 하나 더 만들어서 sum의 최댓값 저장하게끔 해서 풀어봤는데
 * 방문처리가 애매해서 실패
 * 그리고 N,M의 범위가 1,000까지라 bfs/dfs 돌리면 터진다고 한다!
 * 
 * <DP로 풀어야 한다>
 * 진행 가능 방향이 왼쪽, 오른쪽, 아래로 위로는 이동이 불가능하니 한줄씩 최댓값을 계산해준다.
 * 맨 첫 줄은 (0,0)에서 시작해 오른쪽으로만 이동할 수 있다.
 * 둘째 줄부터 각 자리에 오는 방법은 다음 3가지다.
 * 	1. 바로 위에서 내려오기
 * 	2. 왼쪽에서 오기
 * 	3. 오른쪽에서 오기
 * 왼쪽 계산 : 왼쪽 또는 위에서 오는 것 중 큰 값을 저장
 * 오른쪽 계산 : 오른쪽 또는 위에서 오는 것 중 큰 값을 저장
 * 그리고 왼쪽 또는 오른쪽 중 큰 값을 두번째줄 dp에 저장
 * 마지막 줄까지 계산하고 dp[N-1][M-1]을 출력하면 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {

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
		
		// 첫째 줄은 (0,0)에서 시작해 오른쪽으로만 이동할 수 있음
		dp[0][0] = arr[0][0];
		for(int i=1; i<M; i++) {
			dp[0][i] = dp[0][i-1] + arr[0][i];
		}
		
		// 둘째줄부터는 왼쪽, 오른쪽, 위 고려해서 계산
		// 각 자리마다 바로 위에서 내려올 수 있고, 왼쪽 또는 오른쪽에서 올 수 있다
		for(int i=1; i<N; i++) {
			int[] temp1 = new int[M]; // 왼쪽
			int[] temp2 = new int[M]; // 오른쪽
			
			// 왼쪽으로 이동할 때
			temp1[0] = dp[i-1][0] + arr[i][0]; // 왼쪽 맨 끝은 위에서 밖에 내려올 수 없음
			for(int j=1; j<M; j++) {
				temp1[j] = Math.max(temp1[j-1] + arr[i][j], dp[i-1][j] + arr[i][j]);
			}
			
			// 오른쪽으로 이동할 때
			temp2[M-1] = dp[i-1][M-1] + arr[i][M-1]; // 오른쪽 맨 끝은 위에서 밖에 내려올 수 없음
			for(int j=M-2; j>=0; j--) {
				temp2[j] = Math.max(temp2[j+1] + arr[i][j], dp[i-1][j] + arr[i][j]);
			}
			
			// 왼쪽과 오른쪽 중 더 큰 값을 dp에 저장
			for(int j=0; j<M; j++) {
				dp[i][j] = Math.max(temp1[j], temp2[j]);
			}
		}
		
		System.out.println(dp[N-1][M-1]);
	}
	
}