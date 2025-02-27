import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_1699_제곱수의합> - 더 효율적인 풀이
 * N이 11일 때를 기준으로 생각해보자.
 * 11을 만드는 방법은 1+10, 2+9, 3+8, 4+7, 5+6 가 가능하다.
 * 하지만 이런식으로 i/2까지 이중 for문을 돌리면 N이 10만일 때는 시간이 많이 걸린다.
 * 1^2, 2^2, 3^2..인 수는 무조건 항의 최소 개수가 1이다.
 * 따라서 이 수들로 11을 만드는 경우만 비교해도 된다.
 * 그러면 1^2 + 10 -> 1 + dp[10] = 3
 * 		2^2 + 7 -> 1 + dp[7] = 5
 * 		3^2 + 2 -> 1 + dp[2] = 3
 * 이 수들을 비교해 최소 항의 개수를 갱신한다.
 * 그러면 시간을 훨씬 단축시킬 수 있다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
		
		dp[1] = 1;
		
		for(int i=2; i<=N; i++) {
			dp[i] = dp[i-1] + 1; // 초기화 : 이전 수에 1^2을 더하는 경우
			
			for(int j=1; j*j<=i; j++) {
				dp[i] = Math.min(dp[i], 1 + dp[i-j*j]);
			}
		}
		
		System.out.println(dp[N]);
	}

}
