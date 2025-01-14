import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_15988_123더하기3>
 * n은 1,000,000보다 작거나 같은 양수 -> dfs+백트래킹 쓰면 시간초과!
 * DP로 풀어야 한다.
 * 
 * <주의사항>
 * n의 범위가 100만이라 만들 수 있는 경우의 수도 많기 때문에 long 타입을 써야한다.
 * 출력값을 1000000009로 나눈 값으로 정한 이유가 있다는 것을 생각하며 풀자!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int T; // 테스트케이스 수
	static int N; // 1, 2, 3을 더해서 N 만들기 (11보다 작음)
	static long[] dp = new long[1000001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		dp[1] = 1; // 1
		dp[2] = 2; // 1+1, 2
		dp[3] = 4; // 1+1+1, 1+2, 2+1, 3
		
		for(int i=4; i<=1000000; i++) {
			dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000009;
		}
		
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
			System.out.println(dp[N]);
		}
	}

}
