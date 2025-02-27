import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
첫 풀이. 시간이 너무 오래걸림.
메모리 : 15076KB
시간 : 2104ms
*/

public class Main {
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[100001];
		
		dp[1] = 1;
		
		for(int i=2; i<=100000; i++) {
			dp[i] = dp[i-1] + 1; // 초기화
			
			for(int j=1; j<=i/2; j++) {
				if(j * j == i) {
					dp[i] = Math.min(dp[i], 1);
					break;
				}
				dp[i] = Math.min(dp[i], dp[j] + dp[i-j]);
			}
		}
		
		System.out.println(dp[N]);
	}

}
