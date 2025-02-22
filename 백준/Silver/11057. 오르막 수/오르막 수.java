import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static long[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new long[N+1][10];
		
		for(int i=0; i<10; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2; i<=N; i++) {
			for(int j=0; j<10; j++) {
				if(j == 0) {
					dp[i][j] = 1;
					continue;
				}
				dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 10007;
//				dp[i][j] = dp[i][j-1] + dp[i-1][j];
			}
		}
		
		long answer = 0;
		for(int i=0; i<10; i++) {
			answer += dp[N][i];
		}
		System.out.println(answer % 10007);
	}

}
