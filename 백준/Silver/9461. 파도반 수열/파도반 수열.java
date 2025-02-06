import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		long[] dp = new long[101];
		
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		
		for(int i=6; i<=100; i++) {
			dp[i] = dp[i-1] + dp[i-5];
		}
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(dp[N]);
		}
	}

}
