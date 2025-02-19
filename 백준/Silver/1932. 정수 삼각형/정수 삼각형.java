import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1932_정수삼각형>
 * dfs로 풀면 될 것 같지만 범위가 500이므로 시간초과 날 것 같다.
 * 그리고 그리디는 예외가 생기고, 부분 구조를 만족하기에 DP로 풀어야겠다고 생각.
 * 각 dp[i][j]에는 칸까지 올 수 있는 경로의 최댓값을 저장함.
 * (N+1)x(N+1) 크기의 배열로 생각해 볼 때, dp[i][j]칸은 dp[i-1][j-1]과 dp[i-1][j]에서 올 수 있음.
 * 둘 중 최댓값으로 갱신해 각 dp에 저장하면 됨.
 * 생각보다 빠르게 풀 수 있었음. 드디어!!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 삼각형의 크기
	static int[][] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1][N+1];
		dp = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][1] = arr[1][1];
		
		for(int i=2; i<=N; i++) {
			for(int j=1; j<=i; j++) {
				dp[i][j] = Math.max(dp[i-1][j-1] + arr[i][j], dp[i-1][j] + arr[i][j]);
			}
		}
		
		int max = 0;
		for(int i=1; i<=N; i++) {
			max = Math.max(dp[N][i], max);
		}
		System.out.println(max);
	}

}
