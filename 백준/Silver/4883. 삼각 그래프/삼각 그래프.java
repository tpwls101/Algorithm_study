import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_4883_삼각그래프>
 * 비용은 정수이며, 비용의 제곱은 1,000,000보다 작다.
 * 이 말은 즉, 비용이 음수가 올 수도 있다. (간과한 부분!!)
 * 항상 입력값의 조건을 잘 파악해야 한다!!
 * 하지만 다행히 문제 풀이에 지장은 없었다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 그래프의 행의 개수
	static int[][] cost;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) break;
			
			cost = new int[N+1][4];
			dp = new int[N+1][5];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=3; j++) {
					cost[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<=N; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			
			dp[1][2] = cost[1][2];
			dp[1][3] = dp[1][2] + cost[1][3];
			
			for(int i=2; i<=N; i++) {
				for(int j=1; j<=3; j++) {
					int min = Math.min(Math.min(Math.min(dp[i-1][j+1], dp[i-1][j]), dp[i-1][j-1]), dp[i][j-1]);
					dp[i][j] = min + cost[i][j];
				}
			}
			
			sb.append(tc + ". " + dp[N][2] + "\n");
			tc++;
		}
		
		System.out.println(sb);
	}

}
